import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* Класс занимается считыванием и сохранением файлов с месячными отчетами
   в таблицу отдельных оъектов классов:
   месячные отчеты сохраняются в таблицу monthStorage в качестве объектов класса ParseMonthFileData
   годовые отчеты сохраняются в таблицу yearStorage в качестве объектов класса ParseYearFileData
 */

public class fileManager {
    static HashMap<Integer, ArrayList<ParseMonthFileData>> monthStorage;
    static HashMap<Integer, ArrayList<ParseYearFileData>> yearStorage;
    private final String PATH = "..\\java-sprint2-hw\\resources\\";

    public fileManager(){
        monthStorage = new HashMap<>();
        yearStorage = new HashMap<>();
    }

    private static String readFileContentsOrNull(String path) {
        try {

            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public void saveMothsReportsToHashMap() { /* Метод сохраняет распаршенную информацию из подгруженных файлов
                                                 о доходах и расходах за каждый месяц в хэш-таблицу в качестве
                                                 объектов класса ParseMonthFileData из месячных файлов*/
        for (int i = 1; i < 4; i++) {
            ArrayList<ParseMonthFileData> storageList = new ArrayList<>();
            String pathToFile;
            pathToFile = PATH + "m.20210" + i + ".csv";
            String fileContents = readFileContentsOrNull(pathToFile);
            if (fileContents != null) {
                String[] line = fileContents.split(System.lineSeparator());
                for (int j = 1; j < line.length; j++) {
                    String[] lineContents = line[j].split(",");
                    storageList.add(new ParseMonthFileData(lineContents));
                    monthStorage.put(i, storageList);
                }
            } else {
                return;
            }
        }
    }

    public void saveYearReportToHashMap() { /* Метод сохраняет распаршенную информацию из подгруженных файлов
                                               о доходах и расходах за каждый месяц из годового отчета
                                               в хэш-таблицу в качестве объектов класса ParseYearFileData*/
        ArrayList<ParseYearFileData> storageList = new ArrayList<>();
        String pathToFile = PATH + "y.2021.csv";
        String file = readFileContentsOrNull(pathToFile);
        if (file != null) {
            String[] line = file.split(System.lineSeparator());
            for (int i = 1; i < line.length; i++) {
                String[] lineContents = line[i].split(",");
                storageList.add(new ParseYearFileData(lineContents));
                yearStorage.put(2021, storageList);
            }
        }
    }
}
