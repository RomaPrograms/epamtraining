package local;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
public class HamletInternationale {
    public static void main(String[ ] args) {
        //while (true) {
            System.out.println("1 — английский /n 2 — белорусский \n любой — русский ");
            char i = 0;
            try {
                i = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String country = "";
            String language = "";
            switch (i) {
                case '1':
                    country = "US";
                    language = "en";
                    break;
                case '2':
                    country = "BY";
                    language = "be";
                    break;
                case '3':
                    country = "Ru";
                    language = "ru";
            }
            Locale current = new Locale(language, country);
            ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
            String s1 = rb.getString("languageProp");
            System.out.println(s1);
            String s2 = rb.getString("str2");
            System.out.println(s2);
        }
   // }
}
