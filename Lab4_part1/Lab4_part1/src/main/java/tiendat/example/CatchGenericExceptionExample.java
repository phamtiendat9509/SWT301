package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        // Example input that may be null depending on runtime arguments
        String s = (args != null && args.length > 0) ? args[0] : null;
        if (s == null) {
            LOGGER.log(Level.WARNING, "String is null; skipping length computation");
        } else {
            int len = s.length();
            LOGGER.log(Level.INFO, "Length: {0}", len);
        }
    }
}
