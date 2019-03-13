package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Lexeme {
    private List<WorldExpression> arrayList = new ArrayList<>();

    public Lexeme(List<WorldExpression> arrayList) {
        this.arrayList = arrayList;
    }

    public List<WorldExpression> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<WorldExpression> arrayList) {
        this.arrayList = arrayList;
    }
}
