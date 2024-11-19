package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class Wishlist {

    ChromeDriver driver = new ChromeDriver();
    List<WebElement> productCards;
    WebElement firstProductCard;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


    @Given("the user opens the Magento website")
    public void theUserOpensTheMagentoWebsite() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("the user scrolls down to the Hot Sellers section")
    public void theUserScrollsDownToTheHotSellersSection() {
        WebElement hotSellersSection = driver.findElement(By.xpath("//h2[text()='Hot Sellers']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotSellersSection);
    }

    @And("the user hovers over any product card")
    public void theUserHoversOverAnyProductCard() {
        WebElement hotSellersSection = driver.findElement(By.xpath("//h2[text()='Hot Sellers']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hotSellersSection).perform();
        productCards = driver.findElements(By.cssSelector(".product-item-info"));

        if (!productCards.isEmpty()) {
            firstProductCard = productCards.getFirst();
            actions.moveToElement(firstProductCard).perform();
            System.out.println("Hovered over the first product in Hot Sellers section.");
        } else {
            System.out.println("No products found under Hot Sellers.");
        }
    }

    @And("the user clicks on the Add to Wishlist button")
    public void theUserClicksOnTheAddToWishlistButton() {
        if (firstProductCard != null) {
            WebElement wishlistButton = firstProductCard.findElement(By.cssSelector("a.action.towishlist"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", wishlistButton);
            System.out.println("Clicked on 'Add to Wishlist' button.");
        } else {
            System.out.println("Product card not found to click on Wishlist button.");
        }
    }

    @Then("the Guest user should be navigated to the login page")
    public void theGuestUserShouldBeNavigatedToTheLoginPage() {
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS93aXNobGlzdC9pbmRleC9hZGQv/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }


    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/']")).click();
        driver.findElement(By.id("email")).sendKeys("m.khaledmahros@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Mohamed12345");
        driver.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.id("send2")).click();
    }

    @Then("the user should be navigated to the My Wishlist page")
    public void theUserShouldBeNavigatedToTheMyWishlistPage() {
        String expectedUrl = "https://magento.softwaretestingboard.com/wishlist/index/index/wishlist_id/56553/";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "User was not navigated to the My Wishlist page.");
    }

    @And("the product should be added to the Wishlist")
    public void theProductShouldBeAddedToTheWishlist() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-success.message")));
        String messageText = successMessage.getText();
        String expectedText = "Radiant Tee has been added to your Wish List. Click here to continue shopping.";
        Assert.assertEquals(messageText, expectedText, "Confirmation message is not as expected.");
        System.out.println("Confirmation message verified: " + messageText);
    }
}







