package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Calculator;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Client;
import java.util.ArrayList;
import java.util.List;

public class Composit implements Component, Cloneable{

    private List<Component> components = new ArrayList<>();
    private Category category;
    private boolean isOutputText = true;

    public Composit() { }

    public Composit(Category category){
       this.category = category;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public void setIsOutputText(boolean isOutputText) {
        this.isOutputText = isOutputText;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void add(Component component, Category category) {
        this.category = category;
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public Composit clone() {
        Composit composit = this.clone();

//        for (int i = 0; i < composit.getComponents().size(); i++) {
//            composit.getComponents().add(this.getComponents().get(i).clone());
//        }

        return composit;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        for (var component:components) {
            stringBuffer.append(component.toString());
        }

        if (category == Category.WORDANDEXPRESSION) {//Разделить на слова и выражения, чтобы были отдельно слова и отдельно выражения
            if (isOutputText) {
                if (Checker.isNumber(stringBuffer.toString())) {
                    String expression
                            = Calculator.ExpressionToRPN(stringBuffer.toString());
                    stringBuffer.delete(0,
                            stringBuffer.length());
                    Client interpreter = new Client(expression);
                    stringBuffer.append(interpreter.calculate());
                }
                stringBuffer.append(" ");
            }
        } else {
            if (category == Category.SENTENCE) {
                stringBuffer.append("\n");
            }
        }

        return stringBuffer.toString();
    }
}
