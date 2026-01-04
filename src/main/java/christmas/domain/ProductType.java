package christmas.domain;

public enum ProductType {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String description;

    ProductType(String description) {
        this.description = description;
    }

}
