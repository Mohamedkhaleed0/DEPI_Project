package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LandingPage {
    static ChromeDriver driver = new ChromeDriver();
    List<WebElement> productCards;
    WebElement breatheEasyTankCard;

    @Given("The user opens the home page")
    public void theUserOpensTheHomePage() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @When("The user scrolls down to the Hot Sellers section")
    public void theUserScrollsDownToTheHotSellersSection() {
        WebElement hotSellersSection = driver.findElement(By.xpath("//h2[text()='Hot Sellers']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hotSellersSection);
    }

    @Then("The user should see each card with price in dollar")
    public void theUserShouldSeeEachCardWithPriceIn$() {
        List<WebElement> hotSellersCards = driver.findElements(By.cssSelector(".product-item-info"));
        for (WebElement card : hotSellersCards) {
            WebElement priceElement = card.findElement(By.cssSelector(".price"));
            String priceText = priceElement.getText().trim();
            System.out.println("Price: " + priceText);
            if (!priceText.contains("$")) {
                throw new AssertionError("Price does not contain '$': " + priceText);
            }
        }

        System.out.println("All cards display prices in $.");
    }

    @And("Each card should display price as Double, not Integer")
    public void eachCardShouldDisplayPriceAsDoubleNotInteger() {
        List<WebElement> hotSellersCards = driver.findElements(By.cssSelector(".product-item-info"));

        for (WebElement card : hotSellersCards) {
            WebElement priceElement = card.findElement(By.cssSelector(".price"));
            String priceText = priceElement.getText().replace("$", "").trim();
            System.out.println("Price for validation: " + priceText);

            double price = Double.parseDouble(priceText);

            if (!priceText.contains(".")) {
                throw new AssertionError("Price is an integer, not a double: " + priceText);
            }
        }

        System.out.println("All cards display prices as Doubles, not Integers.");
    }

    @Then("Each card should display an Add to Cart button")
    public void eachCardShouldDisplayAnButton() {
        List<WebElement> hotSellersCards = driver.findElements(By.cssSelector(".product-item-info"));
        for (WebElement card : hotSellersCards) {
            WebElement addToCartButton = card.findElement(By.cssSelector(".action.tocart"));

            if (!addToCartButton.isDisplayed()) {
                throw new AssertionError("Add to Cart button is not displayed for a card.");
            }
        }
    }

    @And("The Add to Cart button should be clickable")
    public void theAddToCartButtonShouldBeClickable() {
        List<WebElement> hotSellersCards = driver.findElements(By.cssSelector(".product-item-info"));

        for (WebElement card : hotSellersCards) {
            WebElement addToCartButton = card.findElement(By.cssSelector(".action.tocart"));
            if (!addToCartButton.isDisplayed() || !addToCartButton.isEnabled()) {
                throw new AssertionError("The 'Add to Cart' button is not clickable.");
            }
        }
    }

    @And("I hover on the card Breathe-Easy Tank")
    public void iHoverOnTheBreatheEasyTankCard() {
        productCards = driver.findElements(By.cssSelector(".product-item-info"));

        for (WebElement card : productCards) {
            if (card.getText().contains("Breathe-Easy Tank")) {
                breatheEasyTankCard = card;
                Actions actions = new Actions(driver);
                actions.moveToElement(breatheEasyTankCard).perform();
                System.out.println("Hovered over the Breathe-Easy Tank product card.");
                break;
            }
        }
    }

    @And("I select size M")
    public void iSelectSizeM() {
        WebElement sizeOptions = breatheEasyTankCard.findElement(By.cssSelector("div.swatch-attribute.size"));
        List<WebElement> sizes = sizeOptions.findElements(By.cssSelector("div.swatch-option.text"));

        for (WebElement size : sizes) {
            if (size.getText().equals("M")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", size);  // Click size M using JS to avoid issues
                System.out.println("Selected size M for Breathe-Easy Tank.");
                break;
            }
        }
    }

    @And("I select color Purple")
    public void iSelectColorPurple() {
        WebElement colorOptions = breatheEasyTankCard.findElement(By.cssSelector("div.swatch-attribute.color"));
        List<WebElement> colors = colorOptions.findElements(By.cssSelector("div.swatch-option"));

        for (WebElement color : colors) {
            String colorName = color.getAttribute("aria-label");
            if (colorName.equals("Purple")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", color);  // Click purple color using JS
                System.out.println("Selected purple color for Breathe-Easy Tank.");
                break;
            }
        }    }

    @And("I click on Add to Cart button")
    public void iClickOnAddToCartButton() {
        WebElement addToCartButton = breatheEasyTankCard.findElement(By.cssSelector("button.tocart"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
        System.out.println("Clicked on 'Add to Cart' button.");

    }

    @Then("The product should be added to cart successfully")
    public void theProductShouldBeAddedToCartSuccessfully() {
        WebElement successMessage = driver.findElement(By.cssSelector("div.message-success"));
        String messageText = successMessage.getText();
        String expectedMessage = "You added Breathe-Easy Tank to your shopping cart.";
        assertTrue(messageText.contains(expectedMessage));
    }

    @And("The cart icon should display number 1")
    public void theCartIconShouldDisplayNumber() {
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(20)); // Wait for a max of 10 seconds
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("counter-number")));
        String cartCount = cartIcon.getText().trim();
        assertEquals("1", cartCount);
    }
}

