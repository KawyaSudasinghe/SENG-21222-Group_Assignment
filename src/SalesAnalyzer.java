import java.util.*;

public class SalesAnalyzer {
    private List<Product> products;

    public SalesAnalyzer(List<Product> products) {
        this.products = products;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        Map<String, Double> categoryRevenue = new HashMap<>();

        Product bestSelling = null;
        Product highestRevenue = null;
        double grandTotal = 0.0;

        report.append("============================================\n");
        report.append("       PRODUCT SALES SUMMARY REPORT         \n");
        report.append("============================================\n\n");
        report.append("--- Revenue Per Product ---\n");

        for (Product p : products) {
            double revenue = p.getTotalRevenue();
            grandTotal += revenue;

            // Category mapping
            categoryRevenue.put(p.category, categoryRevenue.getOrDefault(p.category, 0.0) + revenue);

            // Highs and Best Sellers
            if (bestSelling == null || p.quantity > bestSelling.quantity) bestSelling = p;
            if (highestRevenue == null || revenue > highestRevenue.getTotalRevenue()) highestRevenue = p;

            report.append(String.format("%s - %s (%s): $%.2f\n", p.id, p.name, p.category, revenue));
        }

        report.append("\n--- Revenue Per Category ---\n");
        for (Map.Entry<String, Double> entry : categoryRevenue.entrySet()) {
            report.append(String.format("%s: $%.2f\n", entry.getKey(), entry.getValue()));
        }

        report.append("\n--- Highlights ---\n");
        if (bestSelling != null && highestRevenue != null) {
            report.append(String.format("Best-Selling Product : %s (%d units)\n", bestSelling.name, bestSelling.quantity));
            report.append(String.format("Highest Revenue      : %s ($%.2f)\n", highestRevenue.name, highestRevenue.getTotalRevenue()));
        }
        report.append(String.format("Grand Total Revenue  : $%.2f\n", grandTotal));

        return report.toString();
    }
}
