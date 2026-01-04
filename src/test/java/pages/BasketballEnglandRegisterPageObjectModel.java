package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketballEnglandRegisterPageObjectModel {
    //Öka läsbarheten genom att tilldela locators till mer lättförstådda variabler
    //Locators
    private By birthDateField = By.cssSelector("#dp");
    private By firstNameField = By.cssSelector("#member_firstname");
    private By lastNameField = By.cssSelector("#member_lastname");
    private By emailField = By.cssSelector("#member_emailaddress");
    private By confirmEmailField = By.cssSelector("#member_confirmemailaddress");
    private By passwordField = By.cssSelector("#signupunlicenced_password");
    private By confirmPasswordField = By.cssSelector("#signupunlicenced_confirmpassword");
    private By acceptTermsCheckbox = By.cssSelector("label[for='sign_up_25']");
    private By acceptTermsHiddenValue = By.id("sign_up_25");
    private By acceptOver18HiddenValue = By.id("sign_up_26");
    private By acceptOver18Checkbox = By.cssSelector("label[for='sign_up_26']");
    private By acceptCodeOfEthics = By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']");
    private By acceptCodeOfEthicsHiddenValue= By.id("fanmembersignup_agreetocodeofethicsandconduct");
    private By submitButton = By.cssSelector(".form-actions");
    private By errorMessage = By.cssSelector(".field-validation-error");
    private By accountCreationMsg = By.tagName("h2");

    private WebDriver driver;
    private WebDriverWait wait;

    public BasketballEnglandRegisterPageObjectModel(WebDriver driver) {
        this.driver = driver;

        //Initialisera en webdriverWait som ska gälla för alla metoder
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }


    public void open(String url) {
        driver.get(url);
    }


    public void createUser(User user) {
        enterText(birthDateField, user.getBirthDate());
        enterText(firstNameField, user.getFirstName());
        enterText(lastNameField, user.getLastName());
        enterText(emailField, user.getEmail());
        enterText(confirmEmailField, user.getConfirmEmail());
        enterText(passwordField, user.getPassword());
        enterText(confirmPasswordField, user.getConfirmPassword());

        //Om acceptTerms är true kryssa i terms
        if (user.getAcceptTerms()) {
            checkCustomBox(acceptTermsCheckbox, acceptTermsHiddenValue);
        }
        checkCustomBox(acceptOver18Checkbox, acceptOver18HiddenValue);
        checkCustomBox(acceptCodeOfEthics, acceptCodeOfEthicsHiddenValue);

        //Klickar på submit-knappen
        submit(submitButton);
    }


    public WebElement getAccountCreationMessage() {
        WebElement output = waitForElementVisible(accountCreationMsg);
        return output;

    }


    public WebElement getErrorMessage() {
        WebElement output = waitForElementVisible(errorMessage);
        return output;
    }

    private void submit(By locator) {
        waitForElementToBeClickable(locator).submit();
    }


    private void enterText(By locator, String value) {
        waitForElementVisible(locator).sendKeys(value);
    }

    private WebElement waitForElementToBeClickable(By by) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    private WebElement waitForElementVisible(By by) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    private WebElement waitForHiddenInput(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    //Metod för att klicka på custom checkboxar med dolda input-värden
    private void checkCustomBox(By visibleLabel, By hiddenInput) {
        WebElement customBox = waitForElementToBeClickable(visibleLabel);
        WebElement hiddenValue = waitForHiddenInput(hiddenInput);

        if (!hiddenValue.isSelected()) {
            customBox.click();

        }

    }


}