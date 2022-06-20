public class ReportManager {

    MonthlyReport monthlyReport = new MonthlyReport();

    YearlyReport yearlyReport = new YearlyReport();
    public void compareReports() {
        if (!yearlyReport.getYearlyReports().isEmpty() && !monthlyReport.getMonthlyReports().isEmpty()) {
            for (Integer month : yearlyReport.getYearlyReports().keySet()) {
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
            System.out.println("Сверка завершена.");
        } else {
            System.out.println("Сперва загрузите все отчеты.");
        }
    }
}
