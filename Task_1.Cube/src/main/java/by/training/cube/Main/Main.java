package by.training.cube.Main;

import by.training.cube.creator.Creator;
import by.training.cube.entity.Cube;
import by.training.cube.parser.Parser;
import by.training.cube.reader.Reader;

import java.util.ArrayList;

public class Main {
    /**Main is class were we start our program*/

    public static void main(String[] args) {
        Reader reader = new Reader();
        Parser parser = new Parser();
        Creator creator = new Creator();
        ArrayList<String> arrayListStrings = reader.readFromFile("src\\main\\resources\\data1.txt");
        ArrayList<ArrayList<Double>> arrayListsDoubles = parser.parse(arrayListStrings);
        ArrayList<Cube> cubes = creator.create(arrayListsDoubles);

        for(int i =0; i<cubes.size(); i++){
            System.out.println(cubes.get(i));
        }
    }
}
