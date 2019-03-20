package by.training.informhandling.actions;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAction {

    private static final String REGULAR_EXPRESSION
            = "[A-Za-z]+";
    private static final Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    public void sortByNumberOfSentences(Composit composit) {
        Collections.sort(composit.getComponents(), new Comparator<Component>() {
            @Override
            public int compare(Component o1, Component o2) {
                return Integer.compare(o1.getComponents().size(),
                        o2.getComponents().size());
            }
        });
    }

    public void sortWordsByLength(Composit composit) {
        for (var paragraphComponent : composit.getComponents()) {
            for (var sentenceComponent : paragraphComponent.getComponents()) {
            Collections.sort(sentenceComponent.getComponents(), //для компаратора лучше сделать отдельный класс.
                    new Comparator<Component>() {
                @Override
                public int compare(Component o1, Component o2) {
                    o1.setIsOutputText(false);
                    o2.setIsOutputText(false);
                    String wordFromSentence1 = getWordFromLexeme(o1.toString());
                    String wordFromSentence2 = getWordFromLexeme(o2.toString());
                    o1.setIsOutputText(true);
                    o2.setIsOutputText(true);
                    return Integer.compare(wordFromSentence1.length(),
                            wordFromSentence2.length());
                }
            });
            }
        }
    }

    public String getWordFromLexeme(String word) {
            Matcher matcher = pattern.matcher(word);
            while(matcher.find()) {
                return matcher.group();
            }
            return word;

    }

    public void sortLexemByEntranceOfSomeSymbol(Composit composit,
                                                String symbol) {
        Map<Composit, String> arrayList = new HashMap<>();

        for (var paragraph : composit.getComponents()) {
            for (var sentence : paragraph.getComponents()) {
                Collections.sort(sentence.getComponents(),
                        new Comparator<Component>() {
                    @Override
                    public int compare(Component o1, Component o2) {
                        int numberOfEntrance1
                                = getNumberOfEntrance(o1.toString(), symbol);
                        int numberOfEntrance2
                                = getNumberOfEntrance(o2.toString(), symbol);
                        int difference = Integer.compare(numberOfEntrance2,
                                numberOfEntrance1);

                        if (difference == 0) {
                            return o1.toString().compareTo(o2.toString());
                        } else {
                            return difference;
                        }
                    }
                });
            }
        }
    }

    public int getNumberOfEntrance(String lexeme, String symbol) {
        return lexeme.length() - lexeme.replace(symbol, "").length();
    }
}
