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

    public boolean compareReports() {
        boolean equalOrNo = true;
        for (int i = 0; i < monthlyReport.fineMonthlyConsumption().size(); i++){
            if (monthlyReport.fineMonthlyConsumption().get(i).equals(yearlyReport.findYearlyConsumption().get(i))
            && monthlyReport.findMonthlyIncome().get(i).equals(yearlyReport.findYearlyIncome().get(i))){
            } else {
                System.out.println(MONTHS[i] + " не сходится.");
                return false;
            }
        }
        return equalOrNo;
    }

    public void showCompareResults(){
        if (compareReports()){
            System.out.println("Операция завершена успешно.");
        }
    }

    public void showYearReportInfo() { // Выводит информацию из годового отчета
        String year = "2021";
        System.out.println("Рассматриваемый год: " + year); //по тз мы пишем прогу для одного года.
        for (int i = 0; i < yearlyReport.findEachMonthProfit().size(); i++){
            System.out.println("Прибыль за " + MONTHS[i] + ": " + yearlyReport.findEachMonthProfit().get(i));
        }
        yearlyReport.findAverageIncomeAndConsumption(Integer.parseInt(year));
    }

    public void showMonthInfo() { // Выводит информацию из всех месячных отчетов
        for (int i = 1; i < 4; i++) {
            System.out.println(MONTHS[i - 1]);
            monthlyReport.findMostProfitableItem(i);
            monthlyReport.findLargestExpense(i);
            System.out.println();
        }
    }
}


