package by.training.lakes_paradise.action;

public enum Criterion {
    NAME("name"),
    MIN_PRICE("minPrice"),
    MAX_PRICE("maxPrice"),
    NAME_MIN_PRICE("name_minPrice"),
    NAME_MAX_PRICE("name_maxPrice"),
    MIN_PRICE_MAX_PRICE("minPrice_maxPrice"),
    NAME_MIN_PRICE_MAX_PRICE("name_minPrice_maxPrice");

    String criterion;

    Criterion(String criterion) {
        this.criterion = criterion;
    }

    public String getValue() {
        return criterion;
    }
}
