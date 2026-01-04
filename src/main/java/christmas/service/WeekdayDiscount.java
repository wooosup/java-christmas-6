package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements DiscountPolicy {

    private static final int DISCOUNT = 2_023;

    @Override
    public Event getName() {
        return Event.WEEKDAY;
    }

    @Override
    public int calculateDiscount(int date, Order order) {
        DayOfWeek dayOfWeek = getDayOfWeek(date);
        if (isWeekend(dayOfWeek)) {
            return 0;
        }
        return order.countDessert() * DISCOUNT;
    }

    private boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

    private DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(2023, 12, day).getDayOfWeek();
    }

}
