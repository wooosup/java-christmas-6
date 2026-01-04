package christmas.domain;

import static christmas.domain.ProductType.*;

import java.util.List;

public class Order {

    private final List<OrderProduct> orderProducts;

    private Order(List<OrderProduct> orderProducts) {
        validate(orderProducts);
        this.orderProducts = orderProducts;
    }

    public static Order of(List<OrderProduct> orderProducts) {
        return new Order(orderProducts);
    }

    public int calculateTotalPrice() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::calculateTotal)
                .sum();
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    private void validate(List<OrderProduct> orderProducts) {
        if (orderProducts.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        int sum = 0;
        for (OrderProduct product : orderProducts) {
            sum += product.getQuantity();
        }
        if (sum > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int countMain() {
        return orderProducts.stream()
                .filter(o -> o.isType(MAIN))
                .mapToInt(OrderProduct::getQuantity)
                .sum();
    }

    public int countDessert() {
        return orderProducts.stream()
                .filter(o -> o.isType(DESSERT))
                .mapToInt(OrderProduct::getQuantity)
                .sum();
    }
}
