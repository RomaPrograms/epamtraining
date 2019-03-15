package by.training.informhandling.main;

import by.training.informhandling.entity.Text;
import by.training.informhandling.reader.Reader;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String string = reader.readFromFile("src\\main\\resources\\data\\data");
        Text text = new Text(string);

        //String bitExpression1 = "30>>>3";
        //        String bitExpression2 = "~6&9|(3&4)";
        //        String bitExpression3 = "5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)";
        //        String expression = Calculator.ExpressionToRPN(bitExpression3); // expression in polska form
        //        System.out.println(expression);
        //        Client interpreter = new Client(expression);
        //        System.out.println("[ " + expression + " ] = " + interpreter.calculate());

        //System.out.println(string);
    }
}
