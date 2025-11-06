package tests;

import org.junit.jupiter.api.*;
import pages.PracticeFormPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest extends BaseTest {
    static WebDriverWait wait;
    static PracticeFormPage formPage;

    @BeforeAll
    static void init() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        formPage = new PracticeFormPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Fill and submit demoqa practice form")
    void testSubmitPracticeForm() {
        formPage.navigate();

        formPage.setFirstName("Nguyen");
        formPage.setLastName("Van A");
        formPage.setEmail("nguyenvan.a@example.com");
        formPage.selectGender("Male");
        formPage.setMobile("0123456789");
        formPage.setDateOfBirth("10 Nov 1990");
        formPage.addSubject("Maths");
        formPage.selectHobby("Sports");

        // upload picture: use a small file from project root if exists, otherwise skip
        String img = System.getProperty("user.dir") + "\\src\\test\\resources\\test-image.png";
        try {
            formPage.uploadPicture(img);
        } catch (Exception ignored) {
        }

        formPage.setAddress("123 Example Street");
        formPage.selectState("NCR");
        formPage.selectCity("Delhi");

        formPage.submit();

        assertTrue(formPage.isModalVisible(), "Submission modal should be visible after submit");
        String modal = formPage.getModalText();
        assertTrue(modal.contains("Nguyen Van A") || modal.contains("Nguyen Van A".split(" ")[0]), "Modal should contain submitted name");
    }
}
