import tiendat.example.InsecureLogin;
import org.junit.jupiter.api.Test;

class InsecureLoginTest {

    @Test
    void testLoginSuccess() {
        InsecureLogin.login("admin", System.getenv("ADMIN_PASSWORD"));
    }

    @Test
    void testLoginFail() {
        InsecureLogin.login("user", "wrongpassword");
    }

    @Test
    void testPrintUserInfo() {
        new InsecureLogin().printUserInfo("John Doe");
    }
}
