package by.training.auction.entity;

import java.util.Objects;

/**
 * class which contain data about clients of bidding.
 */
public class ClientData {
    /**
     * client name of bidding.
     */
    private String id;
    /**
     * client money.
     */
    private int countOfMoney;

    /**
     * constructor for setting information about client.
     * @param clientId - name of client
     * @param clientCountOfMoney - money of client
     */
    public ClientData(final String clientId, final int clientCountOfMoney) {
        this.id = clientId;
        this.countOfMoney = clientCountOfMoney;
    }

    /**
     * getter for name of client.
     * @return name of client
     */
    public String getId() {

        return id;
    }

    /**
     * setter for name of client.
     * @param clientId - name of client
     */
    public void setId(final String clientId) {
        this.id = clientId;
    }

    /**
     * getter for count of money of current client.
     * @return count of money
     */
    public int getCountOfMoney() {
        return countOfMoney;
    }

    /**
     * setter for count of money.
     * @param clientCountOfMoney - count of money
     */
    public void setCountOfMoney(final int clientCountOfMoney) {
        this.countOfMoney = clientCountOfMoney;
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
        if (!(o instanceof ClientData)) {
            return false;
        }
        ClientData that = (ClientData) o;
        return getCountOfMoney() == that.getCountOfMoney()
                && Objects.equals(getId(), that.getId());
    }

    /**
     * returns hash code of every object of this class.
     * @return hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountOfMoney());
    }

    /**
     * getter for all information about object of this class.
     * @return string with information about object of this class
     */
    @Override
    public String toString() {
        return "ClientData{" + "id='" + id + '\''
                + ", countOfMoney=" + countOfMoney + '}';
    }
}
