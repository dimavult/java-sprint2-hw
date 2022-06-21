import java.io.File;
import java.util.ArrayList;

public class MonthlyService {

    private ArrayList<ParseMonthFileData> monthlyRecords;

    public void saveMonthlyRecords(String path) {
        monthlyRecords = new ArrayList<>();
        File file = new File(path);
        String record = FileReader.readFileContentsOrNull(file.getAbsolutePath());
        if (record != null) {
            String[] line = record.split(System.lineSeparator()); //все так же разные служебные символы в файлах.
            for (int i = 1; i < line.length; i++) {
                String[] lineContent = line[i].split(",");
                monthlyRecords.add(new ParseMonthFileData(lineContent));
            }
        }
    }

    public int getIncome(){
        int income = 0;
        for (ParseMonthFileData monthlyRecord : monthlyRecords) {
            if (!monthlyRecord.getExpense()) {
                income += monthlyRecord.getQuantity() * monthlyRecord.getSumOfOne();
            }
        }
        return income;
    }

    public int getExpense(){
        int expense = 0;
        for (ParseMonthFileData monthlyRecord : monthlyRecords) {
            if (monthlyRecord.getExpense()) {
                expense += monthlyRecord.getQuantity() * monthlyRecord.getSumOfOne();
            }
        }
        return expense;
    }

    public void getMostProfitableProductForTheCurrentMonth(){
        int maxSum = 0;
        String name = "";
        for (ParseMonthFileData monthlyRecord : monthlyRecords){
            int sum = 0;
            if(!monthlyRecord.getExpense()) {
                sum = monthlyRecord.getSumOfOne() * monthlyRecord.getQuantity();
                if (sum > maxSum) {
                    maxSum = sum;
                    name = monthlyRecord.getName();
                }
            }
        }
        System.out.println("Самый прибыльный товар:\n" + name + " - " + maxSum);
    }

    public void getTheBiggestExpenseCurrentMonth(){
        int maxSum = 0;
        String name = "";
        for (ParseMonthFileData monthlyRecord : monthlyRecords){
            int sum = 0;
            if(monthlyRecord.getExpense()) {
                sum = monthlyRecord.getSumOfOne() * monthlyRecord.getQuantity();
                if (sum > maxSum) {
                    maxSum = sum;
                    name = monthlyRecord.getName();
                }
            }
        }
        System.out.println("Самый большая трата:\n" + name + " - " + maxSum);
    }

}
