package christmas.view;

import christmas.domain.Badge;
import christmas.domain.BenefitResult;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.OrderProduct;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    DecimalFormat df = new DecimalFormat("###,###");

    public static void printError(String message) {
        System.out.println(message);
    }

    public void printStart() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printSelectMenu(Order order) {
        System.out.println("<주문 메뉴>");
        for (OrderProduct product : order.getOrderProducts()) {
            System.out.println(product.getProduct().getName() + product.getQuantity() + "개");
        }
        System.out.println();
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(df.format(totalPrice) + "원");
        System.out.println();
    }

    public void printGiftMenu(boolean hasGift) {
        System.out.println("<증정 메뉴>");
        if (hasGift) {
            System.out.println("샴페인 1개");
            System.out.println();
            return;
        }
        System.out.println("없음");
        System.out.println();
    }

    public void printBenefit(BenefitResult benefitResult) {
        System.out.println("<혜택 내역>");

        // 1. 혜택이 하나도 없으면 "없음" 출력하고 종료
        if (benefitResult.hasNoBenefit()) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        // 2. Map에 들어있는 내용만 순회하며 출력
        Map<Event, Integer> discounts = benefitResult.getDiscounts();

        for (Map.Entry<Event, Integer> entry : discounts.entrySet()) {
            String title = entry.getKey().getDescription();
            int amount = entry.getValue();

            System.out.println(title + ": -" + String.format("%,d", amount) + "원");
        }
        System.out.println();
    }

    public void printDiscountedPrice(int discountedPrice) {
        System.out.println("<총혜택 금액>");

        if (discountedPrice > 120000) {
            System.out.println("-" + df.format(discountedPrice + 25000) + "원");
            System.out.println();
            return;
        }
        if (discountedPrice < 10000) {
            System.out.println("0원");
            System.out.println();
            return;
        }
        System.out.println("-" + df.format(discountedPrice) + "원");
        System.out.println();
    }

    public void printFinalPrice(int finalPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(df.format(finalPrice) + "원");
        System.out.println();
    }

    public void printEventBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        if (badge != null) {
            System.out.println(badge.getDescription());
            return;
        }
        System.out.println("없음");
    }
}
