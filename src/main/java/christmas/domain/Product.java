package christmas.domain;

public class Product {

    private final String name;
    private final int price;
    private final ProductType type;

    private Product(String name, int price, ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Product of(String name, int price, ProductType type) {
        return new Product(name, price, type);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
}
