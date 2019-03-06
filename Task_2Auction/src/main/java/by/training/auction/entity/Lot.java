package by.training.auction.entity;

import java.util.Objects;

/**
 * entity class with information about lot.
 */
public class Lot {
    /**
     * name of lot.
     */
    private String name;
    /**
     * start price of lot.
     */
    private int startPrice;

    /**
     * constructor for lot.
     * @param lotName - name of lot
     * @param lotStartPrice - start price of lot
     */
    public Lot(final String lotName, final int lotStartPrice) {
        this.name = lotName;
        this.startPrice = lotStartPrice;
    }

    /**
     * getter for name of lot.
     * @return name of lot
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name of lot.
     * @param lotName - name of lot
     */
    public void setName(final String lotName) {
        this.name = lotName;
    }

    /**
     * getter for start price of lot.
     * @return start price of lot
     */
    public int getStartPrice() {
        return startPrice;
    }

    /**
     * setter for start price of lot.
     * @param lotStartPrice - start price of lot
     */
    public void setStartPrice(final int lotStartPrice) {
        this.startPrice = lotStartPrice;
    }

    /**
     * compare objects with current object of this class.
     * @param o - object for comparing
     * @return true if object equal, false if objects different
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lot)) {
            return false;
        }
        Lot lot = (Lot) o;
        return getStartPrice() == lot.getStartPrice()
                && Objects.equals(getName(), lot.getName());
    }

    /**
     * returns hash code of every object of this class.
     * @return hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartPrice());
    }

    /**
     * getter for all information about object of this class.
     * @return string with information about object of this class
     */
    @Override
    public String toString() {
        return "Lot{" + "name='" + name + '\''
                + ", startPrice=" + startPrice + '}';
    }
}
