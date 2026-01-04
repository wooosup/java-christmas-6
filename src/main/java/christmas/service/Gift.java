package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;

public class Gift implements DiscountPolicy{

    private static final int GIFT_THRESHOLD = 120_000;
    private static final int GIFT_PRICE = 25_000;

    @Override
    public Event getName() {
        return Event.GIFT;
    }

    @Override
    public int calculateDiscount(int date, Order order) {
        if (order.calculateTotalPrice() > GIFT_THRESHOLD) {
            return GIFT_PRICE;
        }
        return 0;
    }

}
