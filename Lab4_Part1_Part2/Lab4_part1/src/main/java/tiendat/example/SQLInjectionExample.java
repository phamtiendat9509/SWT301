package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {
    private static final Logger LOGGER = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        // Intentionally unsafe string concatenation to illustrate SQL injection risk.
        String userInput = "' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        LOGGER.log(Level.INFO, "Executing query: {0}", query);
    }
}
