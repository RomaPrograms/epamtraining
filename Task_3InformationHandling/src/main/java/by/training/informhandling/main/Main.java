package by.training.informhandling.main;

import by.training.informhandling.entity.Text;
import by.training.informhandling.reader.Reader;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String string = reader.readFromFile("src\\main\\resources\\data\\data");
        Text text = new Text(string);

        //System.out.println(string);
    }
}
