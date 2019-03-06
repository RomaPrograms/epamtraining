package by.training.auction.thread;

import by.training.auction.entity.Auction;
import by.training.auction.entity.ClientData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class - thread object of whom we will use for every Client.
 */
public class ClientAction extends Thread {
    /**
     * data about client.
     */
    private ClientData client;
    /**
     * current price for lot.
     */
    private int price;
    /**
     * object of class Auction with information about it.
     */
    private Auction auction;
    /**
     * phaser who will stop Clients for waiting another clients.
     */
    private Phaser phaser;
    /**
     * random for does client want to take part in bidding.
     */
    private boolean random = new Random().nextBoolean();
    /**
     * logger which will write in file our current events.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ClientAction.class);
    /**
     * does it first bidding for that lot.
     */
    private boolean firstBid = true;
    /**
     * max price for first increasing.
     */
    private static final int FIRST_INCREASING = 20;
    /**
     * max price for next increasing.
     */
    private static final int NEXT_INCREASING = 5;
    /**
     * lock for locking some methods.
     */
    private static final Lock lock = new ReentrantLock();
    /**
     * constructor for information about lot and client.
     * @param clientData - client
     * @param personPhaser - phaser for clients
     * @param personAuction - auction
     * @param bidPrice - first price of bid
     */
    public ClientAction(final ClientData clientData, final Phaser personPhaser,
                        final Auction personAuction, final int bidPrice) {
        this.client = clientData;
        this.price = bidPrice;
        this.auction = personAuction;
        this.phaser = personPhaser;
    }

    /**
     * function with actions during bidding.
     */
    public void run() {
        try {
            String message;
            int increasingPrice;
            for (int i = 0; i < auction.getLotsNumber(); i++) {
                long startMilli = System.currentTimeMillis();
                while (random) {

                    if ((System.currentTimeMillis() - startMilli)
                            >= auction.getMaxTimeOfBiddingInMilliseconds()) {
                        message = this.client.getId()
                                + " wanted to increase price, but"
                                + " his time were passed.";
                        LOGGER.info(message);
                        break;
                    }

                    TimeUnit.MILLISECONDS.sleep(
                            auction.getTimeOfSleepingInMilliseconds());

                    if (phaser.getArrivedParties()
                            == auction.getNumberOfClients()
                            && this.auction.getCurrentPriceOfLot() == price) {
                        break;
                    }
                    message = this.client.getId()
                            + " began to think about price...";
                    LOGGER.info(message);
                    TimeUnit.SECONDS.sleep(1);

                    if (phaser.getArrivedParties()
                            != auction.getNumberOfClients()) {

                        if (isEnoughMoney()) {
                            if (firstBid) {
                                price = auction.getCurrentPriceOfLot();
                                increasingPrice =
                                        new Random().nextInt(FIRST_INCREASING);
                                if (isEnoughMoney(increasingPrice)) {
                                    price += increasingPrice;
                                    firstBid = false;
                                } else {
                                    break;
                                }
                            } else {
                                increasingPrice =
                                        new Random().nextInt(NEXT_INCREASING);
                                if (isEnoughMoney(increasingPrice)) {
                                price = auction.getCurrentPriceOfLot()
                                        + NEXT_INCREASING;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }

                        message = client.getId() + " ready to give " + price;
                        LOGGER.info(message);
                    } else {
                        if (phaser.getArrivedParties()
                                == auction.getNumberOfClients()
                                && this.auction.getCurrentPriceOfLot()
                                >= price) {
                            increasingPrice =
                                    new Random().nextInt(NEXT_INCREASING);
                            if (isEnoughMoney(increasingPrice)) {
                                price = auction.getCurrentPriceOfLot()
                                        + increasingPrice;
                            } else {
                                break;
                            }
                            message = client.getId() + " ready to give "
                                    + price;
                            LOGGER.info(message);

                        } else {
                            random = false;
                        }
                    }

                    lock.lock();
                    if (this.price > this.auction.getCurrentPriceOfLot()) {
                        auction.setCurrentPriceOfLot(this.price);
                        auction.setWinner(this.client);
                    }
                    lock.unlock();

                    if (random) {
                        random = new Random().nextBoolean();
                    }

                    TimeUnit.MILLISECONDS.sleep(
                            auction.getTimeOfSleepingInMilliseconds());
                }
                this.phaser.arriveAndAwaitAdvance();
                random = new Random().nextBoolean();
                firstBid = true;
                TimeUnit.MILLISECONDS.sleep(
                        auction.getTimeOfSleepingInMilliseconds());
            }

        } catch (InterruptedException e) {
            LOGGER.error("Bidding were interrupted unexpectedly!");
        }
    }

    /**
     * validate data about money of our clients.
     * @return - true if client have enough money for bidding, false otherwise
     */
    public boolean isEnoughMoney() {
        String message;

        if (this.client.getCountOfMoney()
                < auction.getCurrentPriceOfLot()) {
            message = client.getId() + "does't have enough"
                    + " money for continuation this bidding";
            LOGGER.info(message);
            return false;
        }

        return true;
    }

    /**
     * validate data about money of our clients.
     * @param increasingPrice - additional price for buying lot
     * @return - true if client have enough money for bidding, false otherwise
     */
    public boolean isEnoughMoney(final int increasingPrice) {
        String message;

        if (auction.getCurrentPriceOfLot() + increasingPrice
                > this.client.getCountOfMoney()) {
            message = client.getId() + " does't have enough"
                    + " money for continuation this bidding";
            LOGGER.info(message);
            return false;
        }

        return true;
    }
}
