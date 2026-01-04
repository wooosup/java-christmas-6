package christmas.controller;

import christmas.domain.OrderProduct;
import christmas.domain.Product;
import christmas.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    
    public static List<OrderProduct> parseOrder(String input, ProductRepository repository) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String[] items = input.split(",");

        for (String item : items) {
            String[] parts = item.trim().split("-");
            validateFormat(parts);

            String name = parts[0];
            int quantity = parseQuantity(parts[1]);

            Product product = repository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));

            orderProducts.add(OrderProduct.of(product, quantity));
        }
        return orderProducts;
    }

    private static void validateFormat(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static int parseQuantity(String input) {
        try {
            int quantity = Integer.parseInt(input);
            if (quantity < 1) {
                throw new NumberFormatException();
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}