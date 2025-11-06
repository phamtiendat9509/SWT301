package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PracticeFormPage extends BasePage {
    // Constructor
    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderWrapper = By.id("genterWrapper");
    private By mobile = By.id("userNumber");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbiesWrapper = By.id("hobbiesWrapper");
    private By uploadPicture = By.id("uploadPicture");
    private By currentAddress = By.id("currentAddress");
    private By stateInput = By.id("react-select-3-input");
    private By cityInput = By.id("react-select-4-input");
    private By submitBtn = By.id("submit");
    private By modalDialog = By.className("modal-content");
    private By modalTable = By.className("table-responsive");

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
        // Remove or hide common interfering elements (ads, fixed banners) to avoid click interception
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("var els = document.querySelectorAll('#fixedban, .advertisement, iframe'); for(var i=0;i<els.length;i++){ els[i].style.display='none'; }");
        } catch (Exception ignored) {
        }
    }

    public void setFirstName(String fname) {
        type(firstName, fname);
    }

    public void setLastName(String lname) {
        type(lastName, lname);
    }

    public void setEmail(String mail) {
        type(email, mail);
    }

    public void selectGender(String genderText) {
        // Gender options: Male, Female, Other - located inside wrapper as labels
        WebElement wrapper = waitForVisibility(genderWrapper);
        WebElement option = wrapper.findElement(By.xpath(".//label[text()='" + genderText + "']"));
        option.click();
    }

    public void setMobile(String mobileNumber) {
        type(mobile, mobileNumber);
    }

    public void setDateOfBirth(String value) {
        // The input accepts text; set via JS for reliability (format: 10 Nov 1990)
        WebElement input = waitForVisibility(dateOfBirthInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", input, value);
        // remove focus
        input.sendKeys("\n");
    }

    public void addSubject(String subject) {
        WebElement inp = waitForVisibility(subjectsInput);
        inp.sendKeys(subject);
        inp.sendKeys("\n");
    }

    public void selectHobby(String hobbyText) {
        WebElement wrapper = waitForVisibility(hobbiesWrapper);
        WebElement option = wrapper.findElement(By.xpath(".//label[text()='" + hobbyText + "']"));
        try {
            // prefer JS click to avoid overlay/interception
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", option);
        } catch (Exception e) {
            // fallback to normal click
            option.click();
        }
    }

    public void uploadPicture(String absolutePath) {
        WebElement up = waitForVisibility(uploadPicture);
        up.sendKeys(absolutePath);
    }

    public void setAddress(String address) {
        type(currentAddress, address);
    }

    public void selectState(String stateName) {
        // Click and type the state
        WebElement inp = waitForVisibility(stateInput);
        inp.sendKeys(stateName);
        inp.sendKeys("\n");
    }

    public void selectCity(String cityName) {
        WebElement inp = waitForVisibility(cityInput);
        inp.sendKeys(cityName);
        inp.sendKeys("\n");
    }

    public void submit() {
        // Scroll into view then click submit. Use JS click to avoid overlays.
        WebElement btn = waitForVisibility(submitBtn);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", btn);
            js.executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(btn).click().perform();
        }
    }

    public boolean isModalVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modalDialog));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalText() {
        WebElement tbl = waitForVisibility(modalTable);
        return tbl.getText();
    }
}
