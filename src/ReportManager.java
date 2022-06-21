public class ReportManager { /*Класс отвечает за выведение информации, сравнение отчетов и, так вышло, что за хранение
                               самих отчетов. */

    MonthlyReport monthlyReport = new MonthlyReport();

    YearlyReport yearlyReport = new YearlyReport();
    public void showCompareResult() {
        if (!yearlyReport.getYearlyReports().isEmpty() && !monthlyReport.getMonthlyReports().isEmpty()) {
            for (Integer month : yearlyReport.getYearlyReports().keySet()) { /* Цикл идет по значениям года просто так,
                                                                                он может идти и по значениям месячных
                                                                                отчетов.*/
                compareValues(month);
            }
            System.out.println("Сверка завершена.");
        } else {
            System.out.println("Сперва загрузите все отчеты."); // Если хоть один из отчетов не считан, выдаст предупреждение
        }
    }

    public void compareValues(int month){ //Вынес в отдельный метод для упрощения чтения
        int yearValue = yearlyReport.getExpense(month);
        int monthValue = monthlyReport.getExpense(month);
        if (yearValue != monthValue) {
            System.out.println("Не сходится расход За " + MonthlyReport.MONTHS[month-1]);
        }
        yearValue = yearlyReport.getIncome(month);
        monthValue = monthlyReport.getIncome(month);
        if (yearValue != monthValue) {
            System.out.println("Не сходится доход за " + MonthlyReport.MONTHS[month-1]);
        }
    }

    public void showMonthlyInfo(){
        if (!monthlyReport.getMonthlyReports().isEmpty()) {
            for (Integer i : monthlyReport.getMonthlyReports().keySet()) {
                System.out.println(MonthlyReport.MONTHS[i - 1]);
                monthlyReport.getMonthlyReports().get(i).getMostProfitableProductForTheCurrentMonth();
                monthlyReport.getMonthlyReports().get(i).getTheBiggestExpenseCurrentMonth();
                System.out.println();
            }
        }else {
            System.out.println("Сперва считайте отчеты.");
        }
    }

    public void showYearInfo() {
        if (!yearlyReport.getYearlyReports().isEmpty()) {
            System.out.println("Статистика за 2021 год.");
            for (Integer month : yearlyReport.getYearlyReports().keySet()) {
                System.out.println("Прибыль за " + MonthlyReport.MONTHS[month - 1] + " " + yearlyReport.getProfitEachMonth(month));
            }
            System.out.println("Средний расход за год: " + yearlyReport.getAverageExpense());
            System.out.println("Средний доход за год: " + yearlyReport.getAverageIncome());
        }else {
            System.out.println("Сперва считайте отчет.");
        }
    }
}
