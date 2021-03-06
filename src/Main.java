import java.util.Scanner;

public class Main {

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?.");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выйти из приложения.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportManager reportManager = new ReportManager();

        while (true) {
            printMenu();

            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    reportManager.monthlyReport.saveMonthlyReports();/* Оформил именно так, потому что иначе у меня
                                                                        при сравнении, запрошенные у классов
                                                                        MonthlyReport и YearlyReport данные терялись,
                                                                        оставаясь лишь в этих классах. */
                    break;
                case (2):
                    reportManager.yearlyReport.saveYearReport();
                    break;
                case (3):
                    reportManager.showCompareResult();
                    break;
                case (4):
                    reportManager.showMonthlyInfo();
                    break;
                case (5):
                    reportManager.showYearInfo();
                    break;
                case (0):
                    scanner.close();// Закрыл сканнер
                    return;
                default:
                    System.out.println("Такой команды не существует.");
            }
        }
    }
}