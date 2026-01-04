package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;

public class ChristmasDiscount implements DiscountPolicy{

    @Override
    public Event getName() {
        return Event.CHRISTMAS_D_DAY;
    }

    @Override
    public int calculateDiscount(int date, Order order) {
        if (date > 25) {
            return 0;
        }
        return 1000 + (date - 1) * 100;
    }

}