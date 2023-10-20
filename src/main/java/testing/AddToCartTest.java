package testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCartTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void login() {
        // Input username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Validate successful login (you can add more assertions here)
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));

        try {
            // Tunggu 1 detik
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void addToCart() {
        // Add a product to cart
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Validate product is added to cart (you can add more assertions here)
        assert(driver.findElement(By.className("shopping_cart_badge")).getText().equals("1"));

        try {
            // Tunggu 3 detik
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // menutup browser
        driver.quit();
    }
}
