package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class WorldExpression {
    private List<Symbol> arrayList = new ArrayList<>();

    public WorldExpression(List<Symbol> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Symbol> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Symbol> arrayList) {
        this.arrayList = arrayList;
    }
}
