import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {

    private String logStr;

    public void log(int productNum, int amount) throws IOException {
        logStr = productNum + "," + amount;
        File newFile = new File(Main.logConfig.get(1));
        exportAsCSV(newFile, logStr);
    }

    public void exportAsCSV(File textFile, String lg) throws IOException{
        try (CSVWriter writer = new CSVWriter(new FileWriter(textFile, true))) {
            String[] logSave = lg.split(",");
            writer.writeNext(logSave );
        }
    }
}
