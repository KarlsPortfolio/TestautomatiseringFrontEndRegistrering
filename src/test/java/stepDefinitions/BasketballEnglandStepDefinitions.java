package stepDefinitions;

import data.TestFactory;
import data.UrlProvider;
import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.BasketballEnglandRegisterPageObjectModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketballEnglandStepDefinitions {
    private WebDriver driver;
    BasketballEnglandRegisterPageObjectModel registerPage;

    @Given("I am using {string} as a browser")
    public void iAmUsingAsABrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }


    }

    @And("I am at page {string}")
    public void iAmAtPage(String page) {
        //Använder page som nyckel och hämtar den riktiga url:en från en HashMap i UrlProvider
        String url = UrlProvider.getUrl(page);

        //Initialiserar objekt utifrån POM-klass med WebDriver-instansen
        registerPage = new BasketballEnglandRegisterPageObjectModel(driver);
        registerPage.open(url);
    }

    @When("I register a new user {string}")
    public void iRegisterANewUser(String userType) {
        //Skapar en standardmodell för konton med giltiga värden ifyllda
        User user = TestFactory.getUser(userType);
        //Anropar metod i POM-fil som genomför registrering
        registerPage.createUser(user);

    }

    @Then("I get confirmation message {string}")
    public void iGetConfirmationMessage(String expectedMessage) {
        //Kontrollera att meddelande visas på skärmen och om texten stämmer med testdata
        assert (registerPage.getAccountCreationMessage()).isDisplayed();
        String actualMessage = registerPage.getAccountCreationMessage().getText();
        assertEquals(expectedMessage, actualMessage);

    }

    @When("I register with last name {string}")
    public void iRegisterWith(String lastName) {
        User user = TestFactory.getUser("Standard User");

        //Om testdata är markerad som [empty] ska lastName överskridas med värdet av en tom sträng
        if (lastName.equalsIgnoreCase("[empty]")) {
            lastName = "";

        }
        //Använd testdata från featurefil och överskrid lastName-attributet i user-objektet.
        user.setLastName(lastName);
        registerPage.createUser(user);

    }

    @Then("I get error message {string}")
    public void iGetErrorMessage(String expectedMessage) {
        //Kontrollera att meddelande visas på skärmen och om texten stämmer med testdata
        assert (registerPage.getErrorMessage()).isDisplayed();
        String actualMessage = registerPage.getErrorMessage().getText();
        assertEquals(expectedMessage, actualMessage);

    }

    @When("I register with password {string} and confirm {string}")
    public void iRegisterWithPasswordAndConfirm(String pass, String confirmPass) {
        //Skapar först en standard modell för User med ifyllda värden
        User user = TestFactory.getUser("Standard User");

        //Använd testdata från featurefil och skriv över lösenord
        user.setPassword(pass);
        user.setConfirmPassword(confirmPass);
        registerPage.createUser(user);

    }

    @When("I register with terms accepted {string}")
    public void iRegisterWithTermsAcceptedStatus(String termsAccepted) {
        User user = TestFactory.getUser("Standard User");

        if (termsAccepted.equalsIgnoreCase("true") || termsAccepted.equalsIgnoreCase("false")) {
            //Använd testdata från featurefil, konvertera String till boolean och överskrid termsAccepted i user-objektet
            user.setAcceptTerms(Boolean.parseBoolean(termsAccepted.toLowerCase()));
        } else {
            throw new IllegalArgumentException("Ogiltig data inmatad: " + termsAccepted + "\n Kontrollera i featurefil att termsAccepted är true eller false");

        }
        //Använd testdata från featurefil, konvertera String till boolean och överskrid termsAccepted i user-objektet
        registerPage.createUser(user);
    }

    @After
    public void breakDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Driver stängdes abrupt vid driver.quit(): " + e.getMessage());

            }
        }

    }
}
