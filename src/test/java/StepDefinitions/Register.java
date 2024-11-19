package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Register {

     ChromeDriver driver = new ChromeDriver();

    @Given("I am on the Magento account creation page")
    public void I_am_on_the_Magento_account_creation_page(){
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("I click on the Create an Account button in home page")
    public void iClickOnTheCreateAnAccountButtonInHomePage() {
        driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']")).click();
    }

    @And("I enter a valid First Name")
    public void iEnterAValidFirstName() {
        driver.findElement(By.id("firstname")).sendKeys("Mohamed");
    }

    @And("I enter a valid Last Name")
    public void iEnterAValidLastName() {
        driver.findElement(By.id("lastname")).sendKeys("Khaled");
    }

    @And("I enter a valid Email format")
    public void iEnterAValidEmailFormat() {
        //driver.findElement(By.name("email")).sendKeys("m.khaledmahros@gmail.com");
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
    }

    @And("I enter a Password")
    public void iEnterAPassword() {
        driver.findElement(By.id("password")).sendKeys("Mohamed12345");
    }

    @And("I leave the Confirm Password field empty")
    public void iLeaveTheConfirmPasswordFieldEmpty() {
        //driver.findElement(By.id("password-confirmation")).clear();
    }

    @And("I click on Create an Account")
    public void iClickOnCreateAnAccount() {
        driver.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.cssSelector("button.action.submit.primary")).click();
    }

    @Then("I should see an error message indicating that the Confirm Password field is required")
    public void iShouldSeeAnErrorMessageIndicatingThatTheConfirmPasswordFieldIsRequired() {
        boolean passwordErrorFlag = driver.findElement(By.id("password-confirmation-error")).isDisplayed();
        Assert.assertTrue(passwordErrorFlag);
    }

    @And("Enter Confirm Password does not match Password")
    public void enterConfirmPasswordDoesNotMatchPassword() {
        driver.findElement(By.id("password-confirmation")).sendKeys("Mohamed12345678");
    }

    @Then("I should see an error message indicating that enter the same value in password again")
    public void iShouldSeeAnErrorMessageIndicatingThatEnterTheSameValueInPasswordAgain() {
        boolean passwordConfirmationErrorFlag = driver.findElement(By.id("password-confirmation-error")).isDisplayed();
        Assert.assertTrue(passwordConfirmationErrorFlag);
    }

    @And("Enter Confirm Password match Password")
    public void enterConfirmPasswordMatchPassword() {
        driver.findElement(By.id("password-confirmation")).sendKeys("Mohamed12345");
    }

    @Then("User navigated to account page after click on Create an Account from Homepage")
    public void userNavigatedToAccountPageAfterClickOnCreateAnAccountFromHomepage() {
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

