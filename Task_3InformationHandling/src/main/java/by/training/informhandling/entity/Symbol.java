package by.training.informhandling.entity;

public class Symbol implements TextTree {
    private String symbol;

    public Symbol(String symbol) {
        System.out.println(symbol);
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void print() {
        System.out.print(symbol);
    }
}
