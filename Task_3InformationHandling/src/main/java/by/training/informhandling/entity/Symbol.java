package by.training.informhandling.entity;

public class Symbol implements PrintTree {
    private String symbol;

    public Symbol(String symbol) {
        System.out.println(symbol);
        this.symbol = symbol;
    }

    public void print() {
        System.out.print(symbol);
    }
}
