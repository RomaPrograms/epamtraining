package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private List<Paragraph> arrayList = new ArrayList<>();

    public Text(List<Paragraph> arrayList) {
        this.arrayList = arrayList;
    }

    public List<Paragraph> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Paragraph> arrayList) {
        this.arrayList = arrayList;
    }
}
