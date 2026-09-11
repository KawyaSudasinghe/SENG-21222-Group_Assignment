import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SalesReporter {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java SalesReporter <csv-file-path> <output-method> [output-file-path]");
            System.exit(1);
        }

        String csvPath = args[0];
        String outputMethod = args[1].toLowerCase();

        List<Product> products = new ArrayList<>();

        // Reading the CSV gracefully
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) continue; // Handle missing columns gracefully

                products.add(new Product(
                        values[0].trim(),
                        values[1].trim(),
                        values[2].trim(),
                        Integer.parseInt(values[3].trim()),
                        Double.parseDouble(values[4].trim())
                ));
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.exit(1);
        }

        SalesAnalyzer analyzer = new SalesAnalyzer(products);
        String report = analyzer.generateReport();

        // Output Strategy handling
        OutputStrategy strategy = null;
        if (outputMethod.equals("console")) {
            strategy = new ConsoleOutput();
        } else if (outputMethod.equals("file")) {
            if (args.length < 3) {
                System.err.println("Error: Output file path required when using 'file' method.");
                System.exit(1);
            }
            strategy = new FileOutput(args[2]);
        } else {
            System.err.println("Error: Invalid output method. Use 'console' or 'file'.");
            System.exit(1);
        }

        try {
            strategy.writeReport(report);
        } catch (Exception e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }
}