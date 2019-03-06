package by.training.auction.creator;

import by.training.auction.entity.ClientData;
import by.training.auction.entity.Lot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Creator is class where we can creates our list with clients and list with
 * lots if file have correct data.
 * @author Roman
 * @version 1.0
 */
public class Creator {
    /**
     * creates list with information about clients.
     * @param list - list with primary information
     * @return list with clients
     */
    public List<ClientData> createListOfClients(final Map.Entry<List<Map.
            Entry<String, Integer>>, List<Map.Entry<String, Integer>>> list) {
        List<ClientData> lots = new ArrayList<>();
        for (int i = 0; i < list.getKey().size(); i++) {
            lots.add(new ClientData(list.getKey().get(i).getKey(),
                    list.getKey().get(i).getValue()));
        }
        return lots;
    }

    /**
     * creates list with information about lots.
     * @param list - list with primary information
     * @return list with lots
     */
    public List<Lot> createListOfLots(final Map.Entry<
            List<Map.Entry<String, Integer>>, List<Map.Entry<
            String, Integer>>> list) {
        List<Lot> clients = new ArrayList<>();
        for (int i = 0; i < list.getValue().size(); i++) {
            clients.add(new Lot(list.getValue().get(i).getKey(),
                    list.getValue().get(i).getValue()));
        }
        return clients;
    }
}
