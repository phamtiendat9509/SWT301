package tiendat.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class ResourceLeakExample {
    private static final Logger LOGGER = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.log(Level.INFO, "{0}", line);
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "I/O error while reading data.txt", e);
        }
    }
}
