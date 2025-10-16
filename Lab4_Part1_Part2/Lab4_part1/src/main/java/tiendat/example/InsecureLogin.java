package tiendat.example;

import java.util.logging.Logger;

public class InsecureLogin {
    private static final Logger logger = Logger.getLogger(InsecureLogin.class.getName());

    public static void login(String username, String password) {
        // Đọc mật khẩu từ biến môi trường thay vì hardcode
        String adminPassword = System.getenv("ADMIN_PASSWORD");

        if (username.equals("admin") && password.equals(adminPassword)) {
            logger.info("Login successful");
        } else {
            logger.warning("Login failed");
        }
    }

    public void printUserInfo(String user) {
        if (user != null && !user.isEmpty()) {
            logger.info(() -> String.format("User: %s", user));
        }
    }


}
