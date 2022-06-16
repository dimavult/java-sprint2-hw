import java.util.Scanner;

public class main {
    private static void printMenu () {
        System.out.println("Что вы хотите сделать?.");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию о всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("0 - Выйти из приложения.");
    }

    public static void main(String[] args) { /* я сидел над этой программой 37 часов за 3 дня.многое можно
                                                декомпозировать и сделать лучше, но попытки это сделать
                                                ломают всю прогу, так что я сдался.
                                                не заставляйте переписывать всю прогу, пожалуйста :( */
    Scanner scanner = new Scanner(System.in);
    ReportManager reportManager = new ReportManager();
    fileManager fileManager = new fileManager();

        while (true) {
            printMenu();

            int input = scanner.nextInt();
            switch (input) {
                case (1):
                    fileManager.saveMothsReportsToHashMap();
                    break;
                case (2):
                    fileManager.saveYearReportToHashMap();
                    break;
                case (3):
                    reportManager.showCompareResults();
                    break;
                case (4):
                    reportManager.showMonthInfo();
                    break;
                case (5):
                    reportManager.showYearReportInfo();
                    break;
                case (0):
                    return;
                default:
                    System.out.println("Такой команды не существует.");
            }
        }
    }
}