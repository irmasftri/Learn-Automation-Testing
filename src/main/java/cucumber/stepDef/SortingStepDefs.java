package cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class SortingStepDefs {
    WebDriver driver = new ChromeDriver();

    @Given("User is on the products page")
    public void userIsOnTheProductsPage() {
        driver.get("https://www.saucedemo.com/");
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @When("User selects to sort by {string}")
    public void user_selects_to_sort_by_sort_option(String sortOption) {
        driver.get("https://www.saucedemo.com/inventory.html");
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();

        WebElement option = sortDropdown.findElement(By.cssSelector("option[value='" + sortOption + "']"));
        option.click();
    }

    @Then("Products are sorted by {string}")
    public void productsAreSortedBySort_option(String sortOption) {
        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));

        List<String> names = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        for (WebElement element : productNames) {
            names.add(element.getText());
        }

        for (WebElement element : productPrices) {
            String priceString = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceString));
        }

        boolean sorted = true;

        switch (sortOption) {
            case "Name (A to Z)":
                sorted = isSortedAscending(names);
                break;
            case "Name (Z to A)":
                sorted = isSortedDescending(names);
                break;
            case "Price (low to high)":
                sorted = isSortedAscending(prices);
                break;
            case "Price (high to low)":
                sorted = isSortedDescending(prices);
                break;
        }

        assert sorted;

        try {
            // Tunggu 1 detik agar halaman tidak langsung tertutup
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    private boolean isSortedAscending(List<? extends Comparable> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSortedDescending(List<? extends Comparable> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }
}
