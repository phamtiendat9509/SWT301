package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class UnreachableCodeExample {
    private static final Logger LOGGER = Logger.getLogger(UnreachableCodeExample.class.getName());

    private static final int NUMBER = 42;

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "{0}", NUMBER);
    }
}
