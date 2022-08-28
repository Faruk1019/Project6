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

public class TransferFunds {

    WebDriver driver;
    @Given("User navigate to the website")
    public void user_navigate_to_the_website() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://zero.webappsecurity.com/login.html");
    }

    @Given("User should Login")
    public void user_should_login() {

        WebElement inputUsername = driver.findElement(By.cssSelector("input[id='user_login']"));
        inputUsername.sendKeys("username");

        WebElement inputPassword = driver.findElement(By.cssSelector("input[id='user_password']"));
        inputPassword.sendKeys("password");

        WebElement signInButton = driver.findElement(By.cssSelector("input[value='Sign in']"));
        signInButton.click();

        driver.navigate().back();

    }

    @When("User navigate to Transfer Funds page")
    public void user_navigate_to_transfer_funds_page() {

        WebElement transferFundsButton = driver.findElement(By.cssSelector("span[id='transfer_funds_link']"));
        transferFundsButton.click();

        Select fromAccount = new Select(driver.findElement(By.cssSelector("select[id='tf_fromAccountId']")));
        fromAccount.selectByIndex(1);

        Select toAccount = new Select(driver.findElement(By.cssSelector("select[id='tf_toAccountId']")));
        toAccount.selectByIndex(5);

    }

    @When("User select the {string} of funds")
    public void user_select_the_of_funds(String amount) {
        WebElement amountOfFunds = driver.findElement(By.cssSelector("input[id='tf_amount']"));
        amountOfFunds.sendKeys(amount);

        WebElement description = driver.findElement(By.cssSelector("input[id='tf_description']"));
        description.sendKeys("Crypto");

        WebElement continueButton = driver.findElement(By.cssSelector("button[id='btn_submit']"));
        continueButton.click();

        WebElement submitButton = driver.findElement(By.xpath("//div[@class='pull-right']//button"));
        submitButton.click();
    }

    @Then("User should see transfer success message")
    public void user_should_see_transfer_success_message() {

        WebElement successMessage = driver.findElement(By.cssSelector("div[class='alert alert-success']"));

        Assert.assertTrue(successMessage.getText().toLowerCase().contains("success"));

        driver.quit();

    }

}
