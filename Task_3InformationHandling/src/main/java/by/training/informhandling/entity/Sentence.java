package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<Lexeme> arrayList = new ArrayList<>();

    public Sentence(List<Lexeme> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Lexeme> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Lexeme> arrayList) {
        this.arrayList = arrayList;
    }
}
