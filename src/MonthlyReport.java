import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    private HashMap<Integer, ArrayList<ParseMonthFileData>> monthStorage = new HashMap<>();

    FileManager fileManager = new FileManager();

    public HashMap<Integer, ArrayList<ParseMonthFileData>> getMonthStorage() {
        return monthStorage;
    }

    public void saveMonthlyReport(){
        String nameFile = "";
        for (int i  = 1; i < 12; i++){
            if (i<10){
                nameFile = "m.20210" + i + ".csv";
            }else nameFile = "m.2021" + i + ".csv";
            ArrayList<ParseMonthFileData> storage = new ArrayList<>();
            String path = "resources\\" + nameFile;
            File searchedPath = new File(path);
            String report = fileManager.readFileContentsOrNull(searchedPath.getAbsolutePath());
            if (report != null){
                String[] line = report.split(System.lineSeparator()); /* Почему-то у меня месячные отчеты считывает нормально только
                                                             через System.lineSeparator(), а годовой через \n */
                for (int j = 1; j < line.length; j++){
                    String[] lineContents = line[j].split(",");
                    storage.add(new ParseMonthFileData(lineContents));
                    monthStorage.put(i, storage);
                }
            } else {
                break;
            }
        }
    }
}
