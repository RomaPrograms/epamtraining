package by.training.lakes_paradise.custom;

import javax.servlet.jsp.tagext.TagSupport;

public class HomesteadTag extends TagSupport {
    String name;
    String description;
    String price;
    String numberOfPeople;

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public void setNumberOfPeople(final String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
