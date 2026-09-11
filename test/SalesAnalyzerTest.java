import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SalesAnalyzerTest {

    @Test
    public void testRevenueCalculationAndBestSeller() {
        Product p1 = new Product("P1", "Test1", "Cat1", 10, 5.0); // Revenue 50
        Product p2 = new Product("P2", "Test2", "Cat1", 50, 2.0); // Revenue 100 (Best Seller, Highest Revenue)

        SalesAnalyzer analyzer = new SalesAnalyzer(Arrays.asList(p1, p2));
        String report = analyzer.generateReport();

        assertTrue(report.contains("Grand Total Revenue  : $150.00"));
        assertTrue(report.contains("Highest Revenue      : Test2 ($100.00)"));
        assertTrue(report.contains("Best-Selling Product : Test2 (50 units)"));
    }
}
