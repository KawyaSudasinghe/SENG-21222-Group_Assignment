public class Product {
    String id;
    String name;
    String category;
    int quantity;
    double price;

    public Product(String id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalRevenue() {
        return quantity * price;
    }
}