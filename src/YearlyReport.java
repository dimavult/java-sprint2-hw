import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    private HashMap<Integer, ArrayList<ParseYearFileData>> yearlyReports = new HashMap<>();

    public HashMap<Integer, ArrayList<ParseYearFileData>> getYearlyReports() {
        return yearlyReports;
    }

    private final String PATH = "resources\\y.2021.csv";

    public void saveYearReport() {
        Path checkExists = Paths.get(PATH);
        if (Files.exists(checkExists)) {
            File file = new File(PATH);
            String record = FileReader.readFileContentsOrNull(PATH);
            if (record != null) {
                String[] lines = record.split("\n");
                for (int i = 1; i < lines.length; i += 2) { /* Оформил этот метод имеено так, потому что иначе не получалось
                                                               привязать к одному ключу две строки из файла.*/
                    String[] linesContent = lines[i].split(","); // Первый массив строк для месяца из годового отчета
                    String[] linesContent2 = lines[i + 1].split(",");// второй массив строк для месяца из годового отчета
                    int month = Integer.parseInt(linesContent[0]);// Вытягиваю номер месяца, чтобы присвоить его как ключ в мапу
                    ArrayList<ParseYearFileData> list = new ArrayList<>();// Создал список для хранения инфы за месяц
                    ParseYearFileData parseYearFileData = new ParseYearFileData(linesContent);
                    ParseYearFileData parseYearFileData1 = new ParseYearFileData(linesContent2);
                    list.add(parseYearFileData);
                    list.add(parseYearFileData1);
                    yearlyReports.put(month, list);
                }
            }
            System.out.println("Файл y.2021.csv считан.");
        }
    }

    public int getIncome(int month) {
        int income = 0;
        for (int i = 0; i < yearlyReports.get(month).size(); i++) {
            if (!yearlyReports.get(month).get(i).isExpense()) {
                income = yearlyReports.get(month).get(i).getAmount();
            }
        }
        return income;
    }

    public int getExpense(int month) {
        int expense = 0;
        for (int i = 0; i < yearlyReports.get(month).size(); i++) {
            if (yearlyReports.get(month).get(i).isExpense()) {
                expense = yearlyReports.get(month).get(i).getAmount();
            }
        }
        return expense;
    }

    public int getProfitEachMonth(int month) {
        return getIncome(month) - getExpense(month);
    }

    public int getAverageIncome() {
        int avgIncome = 0;
        for (Integer month : yearlyReports.keySet()) {
            avgIncome += getIncome(month) / yearlyReports.size();
        }
        return avgIncome;
    }

    public int getAverageExpense() {
        int avgExpense = 0;
        for (Integer month : yearlyReports.keySet()) {
            avgExpense += getExpense(month) / yearlyReports.size();
        }
        return avgExpense;
    }

    public void showYearInfo() {
        if (!yearlyReports.isEmpty()) {
            System.out.println("Статистика за 2021 год.");
            for (Integer month : yearlyReports.keySet()) {
                System.out.println("Прибыль за " + MonthlyReport.MONTHS[month - 1] + " " + getProfitEachMonth(month));
            }
            System.out.println("Средний расход за год: " + getAverageExpense());
            System.out.println("Средний доход за год: " + getAverageIncome());
        }else {
            System.out.println("Сперва считайте отчет.");
        }
    }
}

