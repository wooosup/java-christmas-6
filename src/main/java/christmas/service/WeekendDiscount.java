package christmas.service;

import christmas.domain.Event;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements DiscountPolicy{

    private static final int DISCOUNT = 2_023;

    @Override
    public Event getName() {
        return Event.WEEKEND;
    }

    @Override
    public int calculateDiscount(int date, Order order) {
        DayOfWeek dayOfWeek = getDayOfWeek(date);
        if (isWeekend(dayOfWeek)) {
            return order.countMain() * DISCOUNT;
        }
        return 0;
    }

    private boolean isWeekend(DayOfWeek day) {
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY;
    }

    private DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(2023, 12, day).getDayOfWeek();
    }
}
