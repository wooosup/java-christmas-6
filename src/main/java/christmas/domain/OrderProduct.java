package christmas.domain;

public class OrderProduct {

    private final Product product;
    private final int quantity;

    private OrderProduct(Product product, int quantity) {
        validate(quantity);
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderProduct of(Product product, int quantity) {
        return new OrderProduct(product, quantity);
    }

    public int calculateTotal() {
        return product.getPrice() * quantity;
    }

    public boolean isType(ProductType type) {
        return product.getType() == type;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validate(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
