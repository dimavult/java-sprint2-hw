import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    private HashMap<Integer, ArrayList<ParseMonthFileData>> monthStorage = new HashMap<>();

    FileManager fileManager = new FileManager();

    public static class ParseMonthFileData {
        String name;
        boolean isExpense;
        int quantity;
        int sumOfOne;

        public ParseMonthFileData(String[] lines) {
            this.name = lines[0];
            this.isExpense = Boolean.parseBoolean(lines[1]);
            this.quantity = Integer.parseInt(lines[2]);
            this.sumOfOne = Integer.parseInt(lines[3]);
        }
    }

    public void saveMonthlyReport() {/* как сделать так, чтобы метод при наличии хотя бы одного файла с месячным отчетом
                                        не выводил дальше сообщение:
                                        "Невозможно прочитать файл с месячным отчётом...",
                                        а просто выполнял свою функцию для файлов, которые есть в наличии*/
        String nameFile = "";
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                nameFile = "m.20210" + i + ".csv";
            } else nameFile = "m.2021" + i + ".csv";
            ArrayList<ParseMonthFileData> storage = new ArrayList<>();
            String path = "resources\\" + nameFile;
            File searchedPath = new File(path);
            String report = fileManager.readFileContentsOrNull(searchedPath.getAbsolutePath());
            if (report != null) {
                String[] line = report.split(System.lineSeparator()); /* Почему-то у меня месячные отчеты считывает нормально только
                                                             через System.lineSeparator(), а годовой через \n */
                for (int j = 1; j < line.length; j++) {
                    String[] lineContents = line[j].split(",");
                    storage.add(new ParseMonthFileData(lineContents));
                    monthStorage.put(i, storage);
                }
            } else {
                break;
            }
        }
    }

    public ArrayList<Integer> findMonthlyIncome() {
        ArrayList<Integer> incomeList = new ArrayList<>();
        for (Integer integer :
                monthStorage.keySet()) {
            int sum = 0;
            for (int i = 0; i < monthStorage.get(integer).size(); i++) {
                if (!monthStorage.get(integer).get(i).isExpense) {
                    sum += monthStorage.get(integer).get(i).quantity * monthStorage.get(integer).get(i).sumOfOne;
                }
            }
            incomeList.add(sum);
        }
        return incomeList;
    }

    public ArrayList<Integer> fineMonthlyConsumption() {
        ArrayList<Integer> consumptionList = new ArrayList<>();
        for (Integer integer :
                monthStorage.keySet()) {
            int sum = 0;
            for (int i = 0; i < monthStorage.get(integer).size(); i++) {
                if (monthStorage.get(integer).get(i).isExpense) {
                    sum += monthStorage.get(integer).get(i).quantity * monthStorage.get(integer).get(i).sumOfOne;
                }
            }
            consumptionList.add(sum);
        }
        return consumptionList;
    }

    public void findMostProfitableItem(int month) {
        String name = "";
        int maxSum = 0;
        for (int i = 0; i < monthStorage.get(month).size(); i++) {
            int sum = 0;
            if (!monthStorage.get(month).get(i).isExpense) {
                sum += monthStorage.get(month).get(i).quantity * monthStorage.get(month).get(i).sumOfOne;
                if (sum > maxSum) {
                    maxSum = sum;
                    name = monthStorage.get(month).get(i).name;
                }
            }
        }
        System.out.println("Самый прибыльный товар:\n" + name + " - " + maxSum);
    }

    public void findLargestExpense(int month) {
        int maxSum = 0;
        String name = "";
        for (int i = 0; i < monthStorage.get(month).size(); i++) {
            int sum = 0;
            if (monthStorage.get(month).get(i).isExpense) {
                sum += monthStorage.get(month).get(i).quantity * monthStorage.get(month).get(i).sumOfOne;
                if (sum > maxSum) {
                    maxSum = sum;
                    name = monthStorage.get(month).get(i).name;
                }
            }
        }
        System.out.println("Самая большая трата:\n" + name + " - " + maxSum);
    }

}
