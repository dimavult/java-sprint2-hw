import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private static final String PATH = "resources\\y.2021.csv";
    private HashMap<Integer, ArrayList<ParseYearFileData>> yearStorage = new HashMap<>();

    public HashMap<Integer, ArrayList<ParseYearFileData>> getYearStorage() {
        return yearStorage;
    }

    FileManager fileManager = new FileManager();

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
            System.out.println(yearStorage.keySet());
        }
    }
}
