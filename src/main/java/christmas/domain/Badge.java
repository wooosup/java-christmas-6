package christmas.domain;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String description;

    Badge(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Badge findBadge(int totalDiscount) {
        if (totalDiscount >= 20000) {
            return SANTA;
        }
        if (totalDiscount >= 10000) {
            return TREE;
        }
        if (totalDiscount >= 5000) {
            return STAR;
        }
        return null;
    }
}
