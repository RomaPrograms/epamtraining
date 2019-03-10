package by.training.auction.entity;

/**
 * class for additional information about current bids.
 */
public final class Auction {
    /**
     * current price of lot.
     */
    private int currentPriceOfLot;
    /**
     * name of winner.
     */
    private ClientData winner;
    /**
     * number of clients who take part in bidding.
     */
    private int numberOfClients;
    /**
     * max time of bidding in milliseconds.
     */
    private int maxTimeOfBiddingInMilliseconds;
    /**
     * time of waiting clients in milliseconds.
     */
    private int timeOfSleepingInMilliseconds;
    /**
     * lots number during auction.
     */
    private int lotsNumber;
    /**
     * object of class auction.
     */
    private static Auction instance;

    /**
     * private constructor for realization pattern singleton.
     * @param number - number of clients
     * @param maxTimeOfBidding - max time of bidding
     * @param timeOfSleeping - time of sleeping
     * @param lotsNumb - number of lots
     * @param currentPrice - current price
     */
    private Auction(final int number, final int maxTimeOfBidding,
                    final int timeOfSleeping, final int lotsNumb,
                    final int currentPrice) {
        this.numberOfClients = number;
        this.maxTimeOfBiddingInMilliseconds = maxTimeOfBidding;
        this.timeOfSleepingInMilliseconds = timeOfSleeping;
        this.lotsNumber = lotsNumb;
        this.currentPriceOfLot = currentPrice;
    }

    /**
     * getter for object of class auction.
     * @param number - number of clients
     * @param maxTimeOfBidding - max time of bidding
     * @param timeOfSleeping - time of sleeping
     * @param lotsNumb - number of lots
     * @param currentPrice - current price
     * @return object of class auction
     */
    public static synchronized Auction getInstance(final int number,
                                                   final int maxTimeOfBidding,
                                                   final int timeOfSleeping,
                                                   final int lotsNumb,
                                                   final int currentPrice) {
        if (instance == null) {
            instance = new Auction(number, maxTimeOfBidding, timeOfSleeping,
                    lotsNumb, currentPrice);
        }
        return instance;
    }

    /**
     * getter for number of clients.
     * @return number of clients
     */
    public int getNumberOfClients() {
        return numberOfClients;
    }

    /**
     * setter for number of clients.
     * @param number - number of clients
     */
    public void setNumberOfClients(final int number) {
        this.numberOfClients = number;
    }

    /**
     * getter for winner of lot.
     * @return winner of lot
     */
    public ClientData getWinner() {
        return this.winner;
    }

    /**
     * setter for lot winner.
     * @param auctionWinner - winner of lot
     */
    public void setWinner(final ClientData auctionWinner) {
        this.winner = auctionWinner;
    }

    /**
     * getter for current price of lot.
     * @return current price of lot
     */
    public int getCurrentPriceOfLot() {
        return currentPriceOfLot;
    }

    /**
     * setter for current price of some lot.
     * @param currentPrice - current price
     */
    public void setCurrentPriceOfLot(final int currentPrice) {
        this.currentPriceOfLot = currentPrice;
    }

    /**
     * setter for max time of bidding in milliseconds.
     * @return max time of bidding
     */
    public int getMaxTimeOfBiddingInMilliseconds() {
        return maxTimeOfBiddingInMilliseconds;
    }

    /**
     * getter for max time of client waiting.
     * @return max time of client waiting
     */
    public int getTimeOfSleepingInMilliseconds() {
        return timeOfSleepingInMilliseconds;
    }

    /**
     * getter for lots number during bidding.
     * @return lots number
     */
    public int getLotsNumber() {
        return lotsNumber;
    }
}
