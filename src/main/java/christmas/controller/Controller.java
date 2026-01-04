package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.BenefitResult;
import christmas.domain.Order;
import christmas.domain.OrderProduct;
import christmas.repository.ProductRepository;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final EventService eventService;
    private final ProductRepository productRepository;

    public Controller(InputView inputView, OutputView outputView, EventService eventService,
                      ProductRepository productRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventService = eventService;
        this.productRepository = productRepository;
    }

    public void run() {
        // 입력
        outputView.printStart();
        int visitDate = getVisitDate();
        Order order = getOrder();

        // 주문 내역 출력
        outputView.printEventMessage();
        outputView.printSelectMenu(order);

        int totalPrice = order.calculateTotalPrice();
        outputView.printTotalPrice(totalPrice);

        // 혜택 계산 (서비스 호출 -> 결과 Map 받기)
        BenefitResult benefitResult = eventService.calculateBenefits(visitDate, order);

        // 증정 메뉴 출력 (샴페인 여부)
        outputView.printGiftMenu(benefitResult.hasGift());

        // 혜택 내역 출력
        outputView.printBenefit(benefitResult);

        // 할인 금액 계산
        int totalDiscount = benefitResult.getTotalBenefitAmount();
        outputView.printDiscountedPrice(totalDiscount);

        // 할인 한 최종 금액 계산
        int finalPrice = totalPrice - benefitResult.getActualDiscountAmount();
        outputView.printFinalPrice(finalPrice);

        outputView.printEventBadge(Badge.findBadge(totalDiscount));
    }

    private Order getOrder() {
        return retry(() -> {
            String input = inputView.readOrder();
            List<OrderProduct> products = InputParser.parseOrder(input, productRepository);
            return Order.of(products);
        });
    }

    private int getVisitDate() {
        return retry(() -> {
            String input = inputView.readDate();
            validate(input);
            return validateRange(input);
        });
    }

    private void validate(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private int validateRange(String input) {
        int date = Integer.parseInt(input);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return date;
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
