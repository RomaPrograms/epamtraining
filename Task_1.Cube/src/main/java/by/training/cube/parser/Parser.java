package by.training.cube.parser;

import by.training.cube.validator.Validator;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Parser {
    /**This class parses our ArrayList<String> and returns ArrayList<ArrayList<Double>>*/
    private ArrayList<ArrayList<Double>> arrayListDoubles;
    private Validator validator;

    public Parser(){
        validator = new Validator();
        arrayListDoubles = new ArrayList<ArrayList<Double>>();
    }

    public ArrayList<ArrayList<Double>> parse(ArrayList<String> arrayListStrings){
        /**Method parser parses our data from String to Double*/
            for (int i = 0; i < arrayListStrings.size(); i++) {
                if(validator.isRightParameters(arrayListStrings.get(i), i)) {
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    StringTokenizer stringTokenizer = new StringTokenizer(arrayListStrings.get(i), " ");
                    while (stringTokenizer.hasMoreTokens()) {
                        arrayList.add(Double.parseDouble(stringTokenizer.nextToken()));
                    }
                    arrayListDoubles.add(arrayList);
                }
            }
        return arrayListDoubles;
    }
}
