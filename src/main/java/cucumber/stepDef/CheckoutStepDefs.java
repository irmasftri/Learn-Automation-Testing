package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckoutStepDefs {
    WebDriver driver = new ChromeDriver();

    @Given("User has selected a product and added it to the cart")
    public void userHasSelectedAProductAndAddedItToTheCart() {
        //login
        driver.get("https://www.saucedemo.com/");
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Navigasi ke halaman produk
        driver.get("https://www.saucedemo.com/inventory.html");

        // Pilih produk
        WebElement product = driver.findElement(By.xpath("//div[@class='inventory_item'][1]//button[text()='Add to cart']"));
        product.click();
    }

    @When("User proceeds to checkout")
    public void userProceedsToCheckout() {
        // Klik tombol keranjang belanja
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Klik tombol checkout
        WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        checkoutButton.click();
    }

    @When("User enters billing information {string}, {string}, {string}")
    public void userEntersBillingInformation(String name, String address, String zip) {
        // Isi formulir informasi pembayaran
        WebElement nameField = driver.findElement(By.id("first-name"));
        WebElement addressField = driver.findElement(By.id("last-name"));
        WebElement zipField = driver.findElement(By.id("postal-code"));

        nameField.sendKeys(name);
        addressField.sendKeys(address);
        zipField.sendKeys(zip);
    }

    @When("User clicks the 'Continue' button")
    public void userClicksContinueButton() {
        // Klik tombol continue
        WebElement continueButton = driver.findElement(By.className("cart_button"));
        continueButton.click();
    }

    @When("User verifies the order summary")
    public void userVerifiesOrderSummary() {
        // Verifikasi ringkasan pesanan (jika diperlukan)
        // Misalnya, periksa item yang ada di keranjang dan total harga
        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("User clicks the 'Finish' button")
    public void userClicksFinishButton() {
        // Klik tombol finish
        WebElement finishButton = driver.findElement(By.className("cart_button"));
        finishButton.click();
    }

    @Then("User should see the order confirmation")
    public void userShouldSeeOrderConfirmation() {
        // Verifikasi bahwa halaman menunjukkan pesanan telah selesai
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html"));

        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
