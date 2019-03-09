package by.training.auction.validator;

import by.training.auction.entity.Auction;
import by.training.auction.entity.ClientData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class with methods for controlling clients budget.
 */
public class ClientValidator {
    /**
     * logger which will write in file our current events.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ClientValidator.class);
    /**
     * validate data about money of our clients.
     * @param auction - information about auction
     * @param client - information about client
     * @return - true if client have enough money for bidding, false otherwise
     */
    public boolean isEnoughMoney(final Auction auction,
                                 final ClientData client) {
        String message;

        if (client.getCountOfMoney()
                < auction.getCurrentPriceOfLot()) {
            message = client.getId() + " does't have enough"
                    + " money for continuation this bidding";
            LOGGER.info(message);
            return false;
        }

        return true;
    }

    /**
     * validate data about money of our clients.
     * @param client - information about client
     * @param auction - information about auction
     * @param increasingPrice - additional price for buying lot
     * @return - true if client have enough money for bidding, false otherwise
     */
    public boolean isEnoughMoney(final int increasingPrice,
                                 final Auction auction,
                                 final ClientData client) {
        String message;

        if (auction.getCurrentPriceOfLot() + increasingPrice
                > client.getCountOfMoney()) {
            message = client.getId() + " does't have enough"
                    + " money for continuation this bidding";
            LOGGER.info(message);
            return false;
        }

        return true;
    }
}
