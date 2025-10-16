package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class NullPointerExample {
    private static final Logger LOGGER = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = (args != null && args.length > 0) ? args[0] : null;
        if (text != null && !text.isEmpty()) {
            LOGGER.log(Level.INFO, "Text is not empty");
        }
    }
}
