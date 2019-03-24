package by.training.informhandling.actions;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class with all methods and variables for sorting.
 */
public class TextAction {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(TextAction.class);

    /**
     * regular expression that finds words.
     */
    private static final String REGULAR_EXPRESSION = "[A-Za-z]+";

    /**
     * pattern that finds words.
     */
    private static Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    /**
     * comparator which sorts paragraphs by number of sentences.
     */
    private static Comparator<Component>
            comparatorForSortingByNumberOfSentences
            = (o1, o2) -> Integer.compare(o1.getComponents().size(),
                o2.getComponents().size());

    /**
     * comparator which sorts words in every sentences by length of word.
     */
    private static Comparator<Component> comparatorForSortingWordsByLength
            = (o1, o2) -> {
        String wordFromSentence1 = getWordFromLexeme(o1.toString());
        String wordFromSentence2 = getWordFromLexeme(o2.toString());
        return Integer.compare(wordFromSentence1.length(),
                wordFromSentence2.length());
    };

    /**
     * method that returns directly word, without symbols like
     * (",", "." and so on).
     * @param lexeme - lexeme
     * @return word
     */
    private static String getWordFromLexeme(final String lexeme) {
        Matcher matcher = pattern.matcher(lexeme);
        if (matcher.find()) {
            return matcher.group();
        }
        return lexeme;
    }

    /**
     * method sorts paragraphs by number of sentences.
     * @param composit - text tree
     */
    public void sortByNumberOfSentences(final Composit composit) {
        composit.setIsOutputText(false);
        Collections.sort(composit.getComponents(),
                comparatorForSortingByNumberOfSentences);
        LOGGER.info("Sorting paragraphs by number of sentences ended"
                + " successful");
        composit.setIsOutputText(true);
    }

    /**
     * method sorts words in every sentence by length of word.
     * @param composit - text tree
     */
    public void sortWordsByLength(final Composit composit) {
        composit.setIsOutputText(false);
        for (var paragraphComponent : composit.getComponents()) {
            for (var sentenceComponent : paragraphComponent.getComponents()) {
                Collections.sort(sentenceComponent.getComponents(),
                        comparatorForSortingWordsByLength);
            }
        }
        LOGGER.info("Sorting words in every sentence by length of word ended"
                + " successful");
        composit.setIsOutputText(true);
    }

    /**
     * method that sorts lexemes by entrance of some symbol.
     * @param composit - text tree
     * @param symbol - symbol
     * @return list with lexemes
     */
    public String sortLexemesByEntranceOfSomeSymbol(final Composit composit,
            final String symbol) {
        Comparator<String> comparatorForSortingByNumberOfEntrance
                = (o1, o2) -> {
            int numberOfEntrance1
                    = getNumberOfEntrance(o1, symbol);
            int numberOfEntrance2
                    = getNumberOfEntrance(o2, symbol);
            int difference = Integer.compare(numberOfEntrance2,
                    numberOfEntrance1);

            if (difference == 0) {
                return o1.compareTo(o2);
            } else {
                return difference;
            }
        };

        List<String> listWithLexemes = new ArrayList<>();

        for (var paragraph : composit.getComponents()) {
            for (var sentence : paragraph.getComponents()) {
                sentence.setIsOutputText(false);
                for (var lexeme : sentence.getComponents()) {
                    listWithLexemes.add(lexeme.toString());
                }
                sentence.setIsOutputText(true);
            }
        }

        Collections.sort(listWithLexemes,
                comparatorForSortingByNumberOfEntrance);
        LOGGER.info("Sorting lexemes in text by number of entrance of some "
                + "symbol ended successful");

        StringBuilder stringSortedLexemesByEntrance = new StringBuilder();
        for (var lexeme : listWithLexemes) {
            stringSortedLexemesByEntrance.append(lexeme + " ");
        }
        return stringSortedLexemesByEntrance.toString();
    }

    /**
     * returns entrance number of some symbol.
     * @param lexeme - lexeme
     * @param symbol - symbol
     * @return number of entrance symbol in lexeme
     */
    private static int getNumberOfEntrance(final String lexeme,
                                           final String symbol) {
        return lexeme.length() - lexeme.replace(symbol, "").length();
    }
}
