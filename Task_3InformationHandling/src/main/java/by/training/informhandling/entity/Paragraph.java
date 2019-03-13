package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private List<Sentence> arrayList = new ArrayList<>();

    public Paragraph(List<Sentence> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Sentence> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Sentence> arrayList) {
        this.arrayList = arrayList;
    }
}
