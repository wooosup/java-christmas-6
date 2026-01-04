package christmas.domain;

import static christmas.domain.Event.*;

import java.util.Collections;
import java.util.Map;

public class BenefitResult {

    private final Map<Event, Integer> discounts;

    public BenefitResult(Map<Event, Integer> discounts) {
        this.discounts = discounts;
    }

    public static BenefitResult of(Map<Event, Integer> discounts) {
        return new BenefitResult(discounts);
    }

    public boolean hasNoBenefit() {
        return discounts.isEmpty();
    }

    public int getTotalBenefitAmount() {
        return discounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Event, Integer> getDiscounts() {
        return Collections.unmodifiableMap(discounts);
    }

    public boolean hasGift() {
        return discounts.containsKey(GIFT);
    }

    public int getActualDiscountAmount() {
        return discounts.entrySet().stream()
                .filter(entry -> entry.getKey() != GIFT)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

}
