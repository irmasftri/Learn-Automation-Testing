import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Checkout {
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

        // Validasi login sukses
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"));

        try {
            // Tunggu 1 detik
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void testCheckout(){
        // Menambahkan produk ke keranjang
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Validasi produk telah ditambahkan ke keranjang
        assert(driver.findElement(By.className("shopping_cart_badge")).getText().equals("1"));

        try {
            // Tunggu 1 detik
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lanjutkan ke pembayaran
        driver.findElement(By.className("shopping_cart_link")).click();

        // Klik tombol "Checkout".
        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        // Isi informasi pembayaran
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        try {
            // Tunggu 1 detik
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Klik tombol "Lanjutkan".
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();

        // Klik pada tombol "Selesai".
        driver.findElement(By.cssSelector(".btn_action.cart_button")).click();

        // Validasi pembayaran yang berhasil
        assert(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html"));
    }
}
