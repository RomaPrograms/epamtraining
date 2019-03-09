package by.training.auction.parser;

import by.training.auction.validator.ParserValidator;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.HashMap;

/**
 * Parses our ArrayList<String>.
 *
 * @author Roman
 * @version 1.0
 */
public class Parser {
    /**
     * validator for checking valid information.
     */
    private ParserValidator validator = new ParserValidator();
    /**
     * list with information about lots.
     */
    private List<Map.Entry<String, Integer>> lots = new ArrayList<>();
    /**
     * list with information about clients.
     */
    private List<Map.Entry<String, Integer>> clients = new ArrayList<>();

    /**
     * parses our strings with information.
     *
     * @param arrayListStrings - list with information
     * @return lists with information about lots and clients
     */
    public Map.Entry<List<Map.Entry<String, Integer>>,
            List<Map.Entry<String, Integer>>> parse(final List<String>
                                                            arrayListStrings) {
        int i = 0;
        while (!arrayListStrings.get(i).equals("lots:")) {
            if (validator.validate(arrayListStrings.get(i))) {
                StringTokenizer stringTokenizer = new StringTokenizer(
                        arrayListStrings.get(i), " ");
                clients.add(new HashMap.SimpleEntry<>(
                        stringTokenizer.nextToken(),
                        Integer.parseInt(stringTokenizer.nextToken())));
            }
            i++;
        }

        i++;
        while (i != arrayListStrings.size()) {
            if (validator.validate(arrayListStrings.get(i))) {
                StringTokenizer stringTokenizer = new StringTokenizer(
                        arrayListStrings.get(i), " ");
                lots.add(new HashMap.SimpleEntry<>(stringTokenizer.nextToken(),
                        Integer.parseInt(stringTokenizer.nextToken())));
            }
            i++;
        }

        return new HashMap.SimpleEntry<>(clients, lots);
    }
}
