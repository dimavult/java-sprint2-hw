import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {
    final String[] MONTHS = new String[]{"Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнт",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"};

    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();

    public void showCompareResults() { /* В зависимости от вернувшегося из метода compareYearAndMonthStats() значения,
                                          выводит в консоль разный результат.  */
        if (compareYearAndMonthStats()){
            System.out.println("Проверка прошла успешно.");
        }
    }

    public void showYearReportInfo() { // Выводит информацию из годового отчета
        System.out.println("Рассматриваемый год: " + 2021); //по тз мы пишем прогу для одного года.
        printAvgConsumptionAndIncome();
        showProfitEachMonth();
    }

    public void showMonthInfo() { // Выводит информацию из всех месячных отчетов
        for (int i = 1; i < 4; i++) {
            System.out.println(MONTHS[i - 1]);
            findMonthsMostProfitableProduct(i);
            calculateMonthsLargestExpense(i);
            System.out.println();
        }
    }

    public HashMap<Integer, ArrayList<ParseYearFileData>> getYearStorage(){
        return yearlyReport.getYearStorage();
    }

    public HashMap<Integer, ArrayList<ParseMonthFileData>> getMonthStorage(){
        return monthlyReport.getMonthStorage();
    }

    public void calculateMonthsLargestExpense(int monthNum) { // Методов считает самую большую трату за каждый месяц
                                                            // Вероятно, этот метод можно декомпозировать(не понял, как)
        int sum = 0;
        int maxSum = 0;
        String name = "";
        for (int i = 0; i < getMonthStorage().get(monthNum).size();i++){
            if (!getMonthStorage().get(monthNum).get(i).isExpense){
                sum += sumCalculator(monthNum, i);
            }
            if (sum > maxSum){
                maxSum = sum;
                name = getMonthStorage().get(monthNum).get(i).name;
            }
        }
        System.out.println("Cамая большая трата:\n" + name + " - " + maxSum);
    }

    public void findMonthsMostProfitableProduct(int monthNum) { // Метод считает самый прибыльный товар за каждый месяц
                                                            // Вероятно, этот метод можно декомпозировать(не понял, как)
        int sum = 0;
        int maxSum = 0;
        String goodName = "";
        for (int i = 0; i < getMonthStorage().get(monthNum).size(); i++) {
            if (!getMonthStorage().get(monthNum).get(i).isExpense) {
                sum = sumCalculator(monthNum, i);
            }
            if (sum > maxSum) {
                maxSum = sum;
                goodName = getMonthStorage().get(monthNum).get(i).name;
            }
        }
        System.out.println("\nСамый прибыльный товар:\n" + goodName + " - " + maxSum);
    }

    public void printAvgConsumptionAndIncome() {//Метод считает средний расход и доход за год
                                                // Вероятно, этот метод можно декомпозировать(не понял, как)
        int avgConsumption = 0;
        int avgIncome = 0;
        for (int i = 0; i < getYearStorage().get(2021).size(); i++) {
            if (getYearStorage().get(2021).get(i).isExpense) {
                avgConsumption += getYearStorage().get(2021).get(i).amount / (getYearStorage().get(2021).size() / 2);
            } else
                avgIncome +=getYearStorage().get(2021).get(i).amount / (getYearStorage().get(2021).size() / 2);
        }
        System.out.println("Средние траты в месяц: " + avgConsumption + "\nСредний доход в месяц: " + avgIncome);
    }

    public void showProfitEachMonth() { // Методот считает прибыль за каждый месяц
                                        // Вероятно, этот метод можно декомпозировать(не понял, как)
        int month = -1;
        for (int i = 1; i < getYearStorage().get(2021).size(); i += 2) {
            int profit = 0;
            if (getYearStorage().get(2021).get(i).isExpense) {
                profit = getYearStorage().get(2021).get(i - 1).amount - getYearStorage().get(2021).get(i).amount;
            } else {
                profit = getYearStorage().get(2021).get(i).amount - getYearStorage().get(2021).get(i - 1).amount;
            }
            System.out.println("Прибыль за " + MONTHS[month += 1] + ": " + profit + " рублей.");
        }
    }


    public int sumCalculator(int month, int objectNumber) {
        return getMonthStorage().get(month).get(objectNumber).quantity * getMonthStorage().get(month).get(objectNumber).sumOfOne;
    }

    public ArrayList<Integer> getYearStats() { /* Метод возвращает список сумм расходов и доходов по каждому месяцу
                                                  для годовых отчетов. Другой способ сравнения не нашел*/
        ArrayList<Integer> yearList = new ArrayList<>();
        for (int i = 0; i < getYearStorage().get(2021).size(); i+=2) { /* Шаг в два для данного цикла я использую
                                                                                для проверки: если первое значение - трата,
                                                                                я уже знаю, что следующее значение будет
                                                                                доходом и наоборот.*/
            int a = 0;
            if (getYearStorage().get(2021).get(i).isExpense) {
                a = getYearStorage().get(2021).get(i + 1).amount - getYearStorage().get(2021).get(i).amount;
                yearList.add(a);
            } else {
                a = getYearStorage().get(2021).get(i).amount - getYearStorage().get(2021).get(i + 1).amount;
                yearList.add(a);
            }
        }
        return yearList;
    }

    public ArrayList<Integer> getMonthStats(){   /* Метод возвращает список сумм расходов и доходов по каждому месяцу
                                                    для месячных отчетов. */
        ArrayList<Integer> monthList = new ArrayList<>();
        for (Integer monthNum: getMonthStorage().keySet()){
            int consAndIncome = 0;
            for (int i = 0; i < getMonthStorage().get(monthNum).size(); i++){
                if(getMonthStorage().get(monthNum).get(i).isExpense){
                    consAndIncome += -sumCalculator(monthNum, i);
                } else consAndIncome += sumCalculator(monthNum, i);
            }
            monthList.add(consAndIncome);
        }
        return monthList;
    }

    public boolean compareYearAndMonthStats(){ /* Метод возвращает результат сравнения двух списков:
                                                  Списка доходов и расходов из метода getYearStats()
                                                  и писка доходов и расходов из метода getMonthStats()*/
        boolean result = true;
        for (int i = 0; i < getYearStats().size(); i++){
            if (getYearStats().get(i).equals(getMonthStats().get(i))){
            } else {
                System.out.println("Не сходится " + MONTHS[i]);
                result = false;
            }
        }
        return result;
    }
}


