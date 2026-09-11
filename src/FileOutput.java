import java.io.FileWriter;

public class FileOutput implements OutputStrategy {

    private String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeReport(String report) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(report);
        }
    }

}