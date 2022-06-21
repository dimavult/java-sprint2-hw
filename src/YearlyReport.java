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

    public void saveYearReport() {
        String PATH = "resources\\y.2021.csv";
        Path checkExists = Paths.get(PATH);
        if (Files.exists(checkExists)) { // Добавил проверку файла на существование
            File file = new File(PATH);
            String record = FileReader.readFileContentsOrNull(file.getAbsolutePath());
            if (record != null) {
                String[] lines = record.split("\n");//все так же разные служебные символы в файлах.
                saveLinesContentInfo(lines);
            }
            System.out.println("Файл y.2021.csv считан.");
        }
    }

    public void saveLinesContentInfo(String[] lines){ // Мне не нравится этот метод, но я не смог придумать, как сделать его проще.
        for (int i = 1; i < lines.length; i += 2) { /* Оформил этот метод имеено так, потому что иначе не получалось
                                                               привязать к одному ключу две строки из файла.*/
            String[] linesContent = lines[i].split(","); // Первый массив строк для месяца из годового отчета
            String[] linesContent2 = lines[i + 1].split(",");// второй массив для строки №2 того же месяца
            int month = Integer.parseInt(linesContent[0]);// Вытягиваю номер месяца, чтобы присвоить его как ключ в мапу
            ArrayList<ParseYearFileData> list = new ArrayList<>();// Создаю в цикле, чтобы обнулять
            list.add(new ParseYearFileData(linesContent));
            list.add(new ParseYearFileData(linesContent2));
            yearlyReports.put(month, list);/* Не могу вынести из цикла. */
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
}

