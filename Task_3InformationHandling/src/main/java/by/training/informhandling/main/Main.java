package by.training.informhandling.main;

import by.training.informhandling.actions.TextAction;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.parsing.parsingtexttoelements.ParseText;
import by.training.informhandling.parsing.parsingtexttoelements.ParseToParagraph;
import by.training.informhandling.reader.Reader;

public class Main {
    public static void main(final String[] args) {
        Reader reader = new Reader();
        String string = reader.readFromFile("src\\main\\resources\\data\\data");
        TextAction textAction = new TextAction();
        ParseText parseToParagraph = new ParseToParagraph();

        //ParseText parseToParagraph = ParseToParagraph.getParser();

        Composit composit = new Composit();
        parseToParagraph.parse(composit, string);

        textAction.sortByNumberOfSentences(composit);
        //textAction.sortWordsByLength(composit);
        //String textAfterSortingAllLexemes = textAction
                //.sortLexemesByEntranceOfSomeSymbol(composit, "a");
//
//        for (var lexeme : textAfterSortingAllLexemes) {
//            System.out.print(lexeme + " ");
//        }

        System.out.println(composit.toString());
    }
}
