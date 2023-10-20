package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCartStepDefs {
    WebDriver driver = new ChromeDriver();

    @Given("User is logged in")
    public void userIsLoggedIn() {
        driver.get("https://www.saucedemo.com/");
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @When("User clicks the add to cart button")
    public void userClicksAddToCartButton() {
        // Implement steps to click add to cart button
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("User should see the item in the cart")
    public void userShouldSeeItemInCart() {
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_badge"));
        assert(cartIcon.isDisplayed());
        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
