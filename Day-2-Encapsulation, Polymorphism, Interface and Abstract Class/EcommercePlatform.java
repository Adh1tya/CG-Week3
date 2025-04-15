
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

abstract class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculateDiscount();

    public void displayProduct() {
        System.out.println("ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Base Price: $" + price);
    }
}

class Electronics extends Product implements Taxable {
    public Electronics(String productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.1; 
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.18; 
    }

    @Override
    public String getTaxDetails() {
        return "18% GST applied";
    }
}

class Clothing extends Product implements Taxable {
    public Clothing(String productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.2; 
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.05;
    }

    @Override
    public String getTaxDetails() {
        return "5% GST applied";
    }
}

class Groceries extends Product {
    public Groceries(String productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05; 
    }
}

public class EcommercePlatform {
    
    public static void printFinalPrices(Product[] products) {
        for (Product product : products) {
            product.displayProduct();
            double tax = 0;
            String taxInfo = "No tax";

            if (product instanceof Taxable) {
                Taxable taxable = (Taxable) product;
                tax = taxable.calculateTax();
                taxInfo = taxable.getTaxDetails();
            }

            double discount = product.calculateDiscount();
            double finalPrice = product.getPrice() + tax - discount;

            System.out.println("Discount: $" + discount);
            System.out.println("Tax: $" + tax + " (" + taxInfo + ")");
            System.out.println("Final Price: $" + finalPrice);
            
        }
    }

    public static void main(String[] args) {
        Product p1 = new Electronics("E001", "Laptop", 1000);
        Product p2 = new Clothing("C001", "T-Shirt", 500);
        Product p3 = new Groceries("G001", "Rice Bag", 200);

        Product[] products = {p1, p2, p3};

        printFinalPrices(products); 
    }
}
