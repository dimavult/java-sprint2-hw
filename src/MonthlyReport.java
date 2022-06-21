import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    private HashMap<Integer, MonthlyService> monthlyReports = new HashMap<>();

    public HashMap<Integer, MonthlyService> getMonthlyReports() {
        return monthlyReports;
    }

    public static final String[] MONTHS = new String[]{"Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"};

    public void saveMonthlyReports(){
        String fileName = "";
        for (int i = 1; i < 13; i++){
            if (i < 10){
                fileName = "m.20210" + i + ".csv";
            } else {
                fileName = "m.2021" + i + ".csv";
            }
            String filePath = "resources\\" + fileName;
            Path checkExist = Paths.get(filePath);
            if(Files.exists(checkExist)){ // Добавил проверку файла на существование
                MonthlyService monthlyRecord = new MonthlyService();
                monthlyRecord.saveMonthlyRecords(filePath);
                monthlyReports.put(i, monthlyRecord);// Вынес из цикла
                System.out.println("Файл " + fileName + " считан.");
            }
        }
    }

    public int getIncome(int month){
        return monthlyReports.get(month).getIncome();
    }

    public int getExpense(int month){
        return monthlyReports.get(month).getExpense();
    }
}
