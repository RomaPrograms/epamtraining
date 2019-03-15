package by.training.informhandling.entity;

public class Symbol implements PrintTree {
    private String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public void print() {
        System.out.println(symbol);
    }
}
