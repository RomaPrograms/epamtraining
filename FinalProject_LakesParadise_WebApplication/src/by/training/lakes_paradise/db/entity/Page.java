package by.training.lakes_paradise.db.entity;

public enum Page {
    HOMESTEAD("homestead"),
    SIGNUP("signUp"),
    HOME("home");

    String page;

    Page(String page) {
        this.page = page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getValue() {
        return page;
    }
}
