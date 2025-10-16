
package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        int[] arr = new int[5];
        try {
            int value = arr[10];
            LOGGER.log(Level.INFO, "Value at index 10: {0}", value);
        } catch (ArrayIndexOutOfBoundsException e) {

            LOGGER.log(Level.SEVERE, "Attempted to access an invalid array index", e);
        }
    }
}
