package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepDefs {
    WebDriver driver = new ChromeDriver();

    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters {string} and {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @When("User clicks the login button")
    public void userClicksTheLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("User should be redirected to the products page")
    public void userShouldBeRedirectedToTheProductsPage() {
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));
        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Then("User should see an error message")
    public void errorMessage(){
        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
