package action.parsingaction;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Calculator;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculateExpressionAction {
    private String expression1;
    private String expression2;
    private String expression3;

    @BeforeClass
    public void initialiseCalculateExpressionAction() {
        expression1 = "30>>>3";
        expression2 = "~6&9|(3&4)";
        expression3 = "5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)";
    }

    @DataProvider(name = "dataProviderForCalculateBitExpressionAction")
    public Object[][] dataProviderForCalculateBitExpressionAction() {
        return new Object[][] {{expression1, 3}, {expression2, 9},
                {expression3, 5}};
    }

    @Test(dataProvider = "dataProviderForCalculateBitExpressionAction")
    public void calculateBitExpressionAction(String text,
                                       int numberOfParagraphs) {
        Client interpreter = new Client(Calculator.expressionToRPN(text));
        int actual = interpreter.calculate();
        int expected = numberOfParagraphs;
        Assert.assertEquals(actual, expected);
    }
}
