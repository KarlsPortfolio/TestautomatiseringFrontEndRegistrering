package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private By acceptOver18Checkbox = By.cssSelector("label[for='sign_up_26']");
    private By acceptCodeOfEthics = By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']");
    private By submitButton = By.cssSelector(".form-actions");
    private By errorMessage = By.cssSelector(".field-validation-error");
    private By accountCreationMsg = By.tagName("h2");

    private WebDriver driver;
    private WebDriverWait wait;

    public BasketballEnglandRegisterPageObjectModel(WebDriver driver) {
        this.driver = driver;

        //Initialisera en webdriverWait som ska gälla för alla metoder
        wait = new WebDriverWait(driver, Duration.ofSeconds(11));

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
            forceClick(acceptTermsCheckbox);
        }
        forceClick(acceptOver18Checkbox);
        forceClick(acceptCodeOfEthics);

        //Klickar på submit-knappen
        click(submitButton);
    }


    public WebElement getAccountCreationMessage() {
        WebElement output = waitForElementVisible(accountCreationMsg);
        return output;

    }


    public WebElement getErrorMessage() {
        WebElement output = waitForElementVisible(errorMessage);
        return output;
    }

    private void click(By locator) {
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


    private void forceClick(By locator) {
        WebElement hiddenCheckbox = waitForElementToBeClickable(locator);
        //Använder Javascript force click för att klicka på element som är dolda med en knapptryckning (industristandard)
        //Annars skulle alternativet vara att klicka två gånger vilket inte är rekommenderat.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hiddenCheckbox);

    }

}
