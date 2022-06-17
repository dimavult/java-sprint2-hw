import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private static final String PATH = "resources\\y.2021.csv";
    private HashMap<Integer, ArrayList<ParseYearFileData>> yearStorage = new HashMap<>();

    FileManager fileManager = new FileManager();

    public static class ParseYearFileData {
        int month;
        boolean isExpense;
        int amount;

        public ParseYearFileData(String[] lineContents) {
            this.month = Integer.parseInt(lineContents[0]);
            this.isExpense = Boolean.parseBoolean(lineContents[2]);
            this.amount = Integer.parseInt(lineContents[1]);
        }
    }

    public void saveYearlyReport() {
        ArrayList<ParseYearFileData> storageList = new ArrayList<>();
        String file = "";
        File searchedPath = new File(PATH);
        String report = fileManager.readFileContentsOrNull(searchedPath.getAbsolutePath());
        if (report != null) {
            String[] line = report.split("\n");
            for (int i = 1; i < line.length; i++) {
                String[] lineContents = line[i].split(",");
                storageList.add(new ParseYearFileData(lineContents));
                yearStorage.put(2021, storageList);
            }
        }
    }

    public ArrayList<Integer> findYearlyIncome() {
        ArrayList<Integer> incomeList = new ArrayList<>();
        for (int i = 0; i < yearStorage.get(2021).size(); i++) {
            if (!yearStorage.get(2021).get(i).isExpense) {
                incomeList.add(yearStorage.get(2021).get(i).amount);
            }
        }
        return incomeList;
    }

    public ArrayList<Integer> findYearlyConsumption() {
        ArrayList<Integer> consumptionList = new ArrayList<>();
        for (int i = 0; i < yearStorage.get(2021).size(); i++) {
            if (yearStorage.get(2021).get(i).isExpense) {
                consumptionList.add(yearStorage.get(2021).get(i).amount);
            }
        }
        return consumptionList;
    }

    public ArrayList<Integer> findEachMonthProfit(){
        ArrayList<Integer> eachMonthProfit = new ArrayList<>();
        for(int i = 0; i < findYearlyIncome().size(); i++){
            eachMonthProfit.add(findYearlyIncome().get(i) - findYearlyConsumption().get(i));
        }
        return eachMonthProfit;
    }

    public void findAverageIncomeAndConsumption(int year) {
        int avgIncome = 0;
        int avgConsumption = 0;
        for (int i = 0; i < yearStorage.get(year).size(); i++) {
            if (yearStorage.get(year).get(i).isExpense) {
                avgConsumption += yearStorage.get(year).get(i).amount / (yearStorage.get(year).size() / 2);
            } else avgIncome += yearStorage.get(year).get(i).amount / (yearStorage.get(year).size() / 2);
        }
        System.out.println("Средний расход за все месяцы в году: " + avgConsumption);
        System.out.println("Средний доход за все месяцы в году: " + avgIncome);
    }
}
