package StepDefinition;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class PurchaseForeignCurrency {

    WebDriver driver;

    @Given("User navigate to website")
    public void user_navigate_to_website() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://zero.webappsecurity.com/login.html");
    }

    @Given("User enter valid username password")
    public void user_enter_valid_username_password() {
        WebElement inputUsername = driver.findElement(By.cssSelector("input[id='user_login']"));
        inputUsername.sendKeys("username");

        WebElement inputPassword = driver.findElement(By.cssSelector("input[id='user_password']"));
        inputPassword.sendKeys("password");

        WebElement signInButton = driver.findElement(By.cssSelector("input[value='Sign in']"));
        signInButton.click();

        driver.navigate().back();
    }

    @When("User navigate to Purchase Foreign Currency page")
    public void user_navigate_to_purchase_foreign_currency_page() {

        WebElement onlineBankingButton = driver.findElement(By.cssSelector("li[id='onlineBankingMenu']"));
        onlineBankingButton.click();

        WebElement payBillsButton = driver.findElement(By.id("pay_bills_link"));
        payBillsButton.click();

        WebElement purchaseForeignButton = driver.findElement(By.xpath("//a[text()='Purchase Foreign Currency']"));
        purchaseForeignButton.click();

        Select currency = new Select(driver.findElement(By.cssSelector("select[id='pc_currency']")));
        currency.selectByIndex(3);

    }

    @When("User add the currency {string} information")
    public void user_add_the_currency_information(String amount) {

        WebElement currencyAmount = driver.findElement(By.cssSelector("input[id='pc_amount']"));
        currencyAmount.sendKeys(amount);

        WebElement dollarRadioButton = driver.findElement(By.cssSelector("input[id='pc_inDollars_true']"));
        dollarRadioButton.click();

        WebElement purchaseButton = driver.findElement(By.cssSelector("input[id='purchase_cash']"));
        purchaseButton.click();

    }

    @Then("User should see currency success message")
    public void user_should_see_currency_success_message() {

        WebElement successMessage = driver.findElement(By.cssSelector("div[id='alert_content']"));

        Assert.assertTrue(successMessage.getText().toLowerCase().contains("success"));

        driver.quit();
    }

}
