package by.training.auction.main;

import by.training.auction.creator.Creator;
import by.training.auction.entity.Auction;
import by.training.auction.entity.ClientData;
import by.training.auction.entity.Lot;
import by.training.auction.parser.Parser;
import by.training.auction.reader.Reader;
import by.training.auction.thread.ClientAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

/**
 * class launch first thread in program.
 */
public class Main {
    /**
     * logger which will write in file our current events.
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    /**
     * max time of sleeping during auction.
     */
    private static final int MAX_TIME_OF_SLEEPING = 200;
    /**
     * max time of bidding for every lot.
     */
    private static final int MAX_TIME_OF_BIDDING_FOR_EVERY_LOT = 2_400;
    /**
     * object of class Reader for getting information.
     */
    private static final Reader reader = new Reader();
    /**
     * object of class Parser for parsing information.
     */
    private static final Parser parser = new Parser();
    /**
     * object of class Creator for processing information.
     */
    private static final Creator creator = new Creator();
    /**
     * list with our threads.
     */
    private static final List<ClientAction> clientActions
            = new ArrayList<>();
    /**
     * main function where we launch our threads.
     * @param args some arguments which we can enter from command line
     */
    public static void main(final String[] args) {
        String message;
        String fileName = "src\\main\\resources\\data\\data";
        List<String> list = reader.readFromFile(fileName);
        Map.Entry<List<Map.Entry<String, Integer>>,
                List<Map.Entry<String, Integer>>> pair = parser.parse(list);

        List<Lot> lots = creator.createListOfLots(pair);
        List<ClientData> clients = creator.createListOfClients(pair);

        Phaser phaser = new Phaser(clients.size() + 1);
        Auction auction = new Auction(clients.size(),
                MAX_TIME_OF_BIDDING_FOR_EVERY_LOT,
                MAX_TIME_OF_SLEEPING, lots.size(), lots.get(0).getStartPrice());

        message = "So, first Lot is \"" + lots.get(0).getName()
                + "\". Start price is : " + lots.get(0).getStartPrice()
                + ". Lets start BIDDING!";
        LOGGER.info(message);

        for (int i = 0; i < clients.size(); i++) {
            ClientAction clientAction = new ClientAction(clients.get(i),
                    phaser, auction, lots.get(0).getStartPrice());
            clientActions.add(clientAction);
        }

        for (int i = 0; i < clientActions.size(); i++){
            clientActions.get(i).start();
        }

        startBidding(lots, phaser, auction);
    }

    /**
     * starts our bidding with all clients and lots.
     * @param lots - bidding lots
     * @param phaser - phaser
     * @param auction - data about auction
     */
    public static void startBidding(List<Lot> lots, Phaser phaser,
                                    Auction auction) {
        String message;

        for (int i = 0; i < lots.size(); i++) {
            phaser.arriveAndAwaitAdvance();

            if (lots.get(i).getStartPrice() == auction.getCurrentPriceOfLot()) {
                message =  "Loot number " + i + " with "
                        + lots.get(i).getName()
                        + " wasn't sold. Nobody wanted to buy it.\n";
            } else {
                message = "Loot number " + i + " with "
                        + lots.get(i).getName()
                        + " was sold! It was sold for the "
                        + auction.getCurrentPriceOfLot() + "$ to "
                        + auction.getWinner().getId() + ".\n";
                auction.getWinner().setCountOfMoney(auction.getWinner().
                        getCountOfMoney() - auction.getCurrentPriceOfLot());
            }

            LOGGER.info(message);

            if (lots.size() != i + 1) {
                auction.setCurrentPriceOfLot(lots.get(i + 1).getStartPrice());
                message = "So, next Lot is \"" + lots.get(i + 1).getName()
                + "\". Start price is : " + lots.get(i + 1).getStartPrice()
                        + ". Lets start BIDDING!";
                LOGGER.info(message);
            }
        }
    }
}
