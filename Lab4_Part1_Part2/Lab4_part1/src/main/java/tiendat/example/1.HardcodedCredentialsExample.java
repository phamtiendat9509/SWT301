package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Objects;

class HardcodedCredentialsExample {
    private static final Logger LOGGER = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        // Read credentials from args or environment instead of hardcoding
        String username = (args != null && args.length > 0) ? args[0] : System.getenv("APP_USER");
        String password = (args != null && args.length > 1) ? args[1] : System.getenv("APP_PASS");

        boolean ok = authenticate(username, password);
        if (ok) {
            LOGGER.log(Level.INFO, "Access granted");
        } else {
            LOGGER.log(Level.WARNING, "Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Example only: compare against env-provided values when present
        String expectedUser = Objects.requireNonNullElse(System.getenv("APP_USER"), "admin");
        String expectedPass = Objects.requireNonNullElse(System.getenv("APP_PASS"), "123456");
        return Objects.equals(user, expectedUser) && Objects.equals(pass, expectedPass);
    }
}
