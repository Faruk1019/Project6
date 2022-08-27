package StepDefinition;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AddNewPayee {

    WebDriver driver;

    @Given("User navigate to webapp website")
    public void user_navigate_to_webapp_website() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://zero.webappsecurity.com/login.html");

    }

    @Given("User enter valid username and password")
    public void user_enter_valid_username_and_password() {
        WebElement inputUsername = driver.findElement(By.cssSelector("input[id='user_login']"));
        inputUsername.sendKeys("username");

        WebElement inputPassword = driver.findElement(By.cssSelector("input[id='user_password']"));
        inputPassword.sendKeys("password");

        WebElement signInButton = driver.findElement(By.cssSelector("input[value='Sign in']"));
        signInButton.click();

        driver.navigate().back();

    }

    @When("User navigate to Add New Payee page")
    public void user_navigate_to_add_new_payee_page() {
        WebElement onlineBankingButton = driver.findElement(By.id("onlineBankingMenu"));
        onlineBankingButton.click();

        WebElement payBillsButton = driver.findElement(By.id("pay_bills_link"));
        payBillsButton.click();

        WebElement addNewPayeeButton = driver.findElement(By.xpath("//a[text()='Add New Payee']"));
        addNewPayeeButton.click();

    }

    @When("User add the payee {string} information")
    public void user_add_the_payee_information(String payeePerson) {

        WebElement payeeName = driver.findElement(By.cssSelector("input[id='np_new_payee_name']"));
        payeeName.sendKeys(payeePerson);

        WebElement payeeAddress = driver.findElement(By.cssSelector("textarea[id='np_new_payee_address']"));
        payeeAddress.sendKeys("111 Garden St");

        WebElement payeeAccount = driver.findElement(By.cssSelector("input[id='np_new_payee_account']"));
        payeeAccount.sendKeys("Account1");

        WebElement payeeDetails = driver.findElement(By.cssSelector("input[id='np_new_payee_details']"));
        payeeDetails.sendKeys("New Home");

        WebElement payeeAddButton = driver.findElement(By.cssSelector("input[id='add_new_payee']"));
        payeeAddButton.click();


    }

    @Then("User should see success message")
    public void user_should_see_success_message() {
        WebElement successMessage = driver.findElement(By.cssSelector("div[id='alert_content']"));
        Assert.assertTrue(successMessage.getText().toLowerCase().contains("success"));

        driver.quit();

    }

}
