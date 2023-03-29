import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    private List<String> log = new ArrayList<>();
    private String logStr;

    File newFile = new File("log.csv");

    public void log(int productNum, int amount) {
        log.add(Integer.toString(productNum));
        log.add(Integer.toString(amount));
        logStr = String.join(", ", log);
        exportAsCSV(newFile, logStr);
    }

    public void exportAsCSV(File textFile, String lg) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(textFile, true))) {
            String[] logSave = lg.split(",");
            writer.writeNext(logSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
