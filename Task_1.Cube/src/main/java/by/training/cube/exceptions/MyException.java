package by.training.cube.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyException extends Exception{
    /**MyException is class with Exceptions*/

    static final Logger LOGGER = LogManager.getLogger(MyException.class.getName());

    public MyException(String str) {
        super(str);
        LOGGER.error(str);
    }
}
