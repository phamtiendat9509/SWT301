package tiendat.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class PathTraversalExample {
    private static final Logger LOGGER = Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) {
        String userInput = "../secret.txt"; // demo value only
        File file = new File(userInput);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                LOGGER.log(Level.INFO, "Reading file: {0}", file.getPath());
                // intentionally not reading content; demo only
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, ex, () -> String.format("Failed to open file: %s", file.getPath()));
            }
        }
    }
}
