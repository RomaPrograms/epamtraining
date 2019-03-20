package by.training.informhandling.main;

import by.training.informhandling.actions.TextAction;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.parsing.parsingtext.ParseToParagraph;
import by.training.informhandling.reader.Reader;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String string = reader.readFromFile("src\\main\\resources\\data\\data");
        TextAction textAction = new TextAction();
        ParseToParagraph parseToParagraph = new ParseToParagraph();
        Composit composit = new Composit();
        parseToParagraph.parse(composit, string);
        //textAction.sortByNumberOfSentences(composit);
        textAction.sortWordsByLength(composit);
        //textAction.sortLexemByEntranceOfSomeSymbol(composit, ",");
        Composit composit1 = composit.clone();
        System.out.println(composit.toString());

        //Composit composit1 = composit.clone();
    }
}
