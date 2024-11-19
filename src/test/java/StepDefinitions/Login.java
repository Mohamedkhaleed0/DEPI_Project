package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Login {

    ChromeDriver driver = new ChromeDriver();

    @Given("the user on the Magento login page")
    public void theUserOnTheMagentoLoginPage() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("the user clicks on the Sign In button in home page")
    public void theUserClicksOnTheSignInButton() {
        driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/']")).click();
    }

    @And("the user enters the email registeredemail@example.com")
    public void theUserEntersTheEmailRegisteredemailExampleCom() {
        driver.findElement(By.id("email")).sendKeys("m.khaledmahros@gmail.com");
    }

    @And("the user enters the password")
    public void theUserEntersThePassword() {
        driver.findElement(By.id("pass")).sendKeys("Mohamed12345");
    }

    @And("the user clicks on the Sign In button")
    public void theUserClicksOnTheButton() {
        driver.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.id("send2")).click();
    }

    @Then("the user should be navigated to the homepage")
    public void theUserShouldBeNavigatedToTheHomepage() {
        String expectedUrl = "https://magento.softwaretestingboard.com/";
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Successfully navigated to the homepage.");
        } else {
            System.out.println("Navigation to the homepage failed. Actual URL: " + actualUrl);
        }
    }


    @And("the welcome message should contain the user's first name Mohamed and last name Khaled")
    public void theWelcomeMessageShouldContainTheUserSFirstNameMohamedAndLastNameKhaled() throws InterruptedException {
        Thread.sleep(1000);
        boolean loginSuccessfullyFlag = driver.findElement(By.cssSelector("span.logged-in")).isDisplayed();
        Assert.assertTrue(loginSuccessfullyFlag);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser after each scenario
        }
    }
}