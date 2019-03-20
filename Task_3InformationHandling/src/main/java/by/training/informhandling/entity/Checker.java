package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Calculator;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    private static final String REGULAR_EXPRESSION_FOR_EXPRESIONS
            = "[\\d&~|^<>()][\\d&~|^<>()]+";

    public static boolean isNumber(String value) {
        Matcher matcher1 = Pattern
                .compile(REGULAR_EXPRESSION_FOR_EXPRESIONS).matcher(value);
        if (matcher1.find()) {
            return true;
        }
        return false;
    }
}
