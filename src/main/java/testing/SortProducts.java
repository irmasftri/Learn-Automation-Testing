package testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SortProducts {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Input username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Validate successful login (you can add more assertions here)
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    public void testSortProducts() {
        // Navigate to inventory page
        driver.get("https://www.saucedemo.com/inventory.html");

        // Click on the "Sort" dropdown
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();

        // Select "Price (low to high)" option
        WebElement priceLowToHigh = sortDropdown.findElement(By.cssSelector("option[value='lohi']"));
        priceLowToHigh.click();

        // Get list of product prices
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));

        // Check if the products are sorted correctly
        for (int i = 0; i < productPrices.size() - 1; i++) {
            String price1 = productPrices.get(i).getText().substring(1); // Removing "$" sign
            String price2 = productPrices.get(i + 1).getText().substring(1);

            double productPrice1 = Double.parseDouble(price1);
            double productPrice2 = Double.parseDouble(price2);

            // Assert that product prices are in ascending order
            assert (productPrice1 <= productPrice2);
        }
    }

    @After
    public void tearDown() {
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
