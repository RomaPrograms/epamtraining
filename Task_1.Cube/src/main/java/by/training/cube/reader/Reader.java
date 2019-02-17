package by.training.cube.reader;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    /**Reader is class which gets information from our file.*/
    ArrayList<String> arrayList;

    public Reader(){
        arrayList = new ArrayList<String>();
    }

    public ArrayList<String> readFromFile(String file){
        /**Method reader reads data from file.*/
        String str;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(file));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                str = bufferedReader.readLine();
                if(str.equals("")){

                }else {
                    arrayList.add(str);
                }
            }
        }catch(FileNotFoundException ex){
            new Exception("File wasn't found. Please, check name of the file.");
        }
        catch(IOException ex){
            new Exception("File has incorrect data, please refactor your file.");
        }
        finally{
            try {
                if(fileReader!=null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                new Exception("File can't be closed, you have problems with your file.");
            }

        }
        return arrayList;
    }
}
