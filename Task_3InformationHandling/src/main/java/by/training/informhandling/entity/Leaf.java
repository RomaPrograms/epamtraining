package by.training.informhandling.entity;

import java.util.List;

public class Leaf implements Component, Cloneable{
    private String value; //Нельзя хранить стринг, нам нужно хранить чар

    public Leaf(String symbol) {
        value = symbol;
    }

    @Override
    public void setIsOutputText(boolean isOutputText) {
        System.out.println("-------------");
    }

    public void operation() {
        System.out.println("Leaf -> Performing operation");
    }
    public void add(Component c, Category category) {
        System.out.println("Leaf -> add. Doing nothing");
        // генерация исключения и return false (если метод не void)
    }
    public void remove(Component c) {
        System.out.println("Leaf -> remove. Doing nothing");
        // генерация исключения и return false (если метод не void)
    }
    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public Leaf clone() {
        return this.clone();
    }
}
