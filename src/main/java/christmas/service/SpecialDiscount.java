package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount implements DiscountPolicy {

    private static final int DISCOUNT = 1_000;

    @Override
    public Event getName() {
        return Event.SPECIAL;
    }

    @Override
    public int calculateDiscount(int date, Order order) {
        DayOfWeek dayOfWeek = getDayOfWeek(date);
        if (dayOfWeek == DayOfWeek.SUNDAY || date == 25) {
            return DISCOUNT;
        }
        return 0;
    }

    private DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(2023, 12, day).getDayOfWeek();
    }

}
