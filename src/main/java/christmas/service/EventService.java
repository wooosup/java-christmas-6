package christmas.service;

import christmas.domain.BenefitResult;
import christmas.domain.Event;
import christmas.domain.Order;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventService {

    private final List<DiscountPolicy> policies = List.of(
            new ChristmasDiscount(),
            new WeekdayDiscount(),
            new WeekendDiscount(),
            new SpecialDiscount(),
            new Gift()
    );

    public BenefitResult calculateBenefits(int date, Order order) {
        Map<Event, Integer> discounts = new EnumMap<>(Event.class);

        for (DiscountPolicy policy : policies) {
            int amount = policy.calculateDiscount(date, order);

            if (amount > 0) {
                discounts.put(policy.getName(), amount);
            }
        }
        return BenefitResult.of(discounts);
    }

}
