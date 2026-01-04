package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;

public interface DiscountPolicy {

    Event getName();

    int calculateDiscount(int date, Order order);
}
