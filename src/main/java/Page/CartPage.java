package Page;

import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends Page {

    private WebDriver driver;
    private Wait wait;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartBtn;
    @FindBy(xpath = "//*[@class='icon-trash']")
    private WebElement removeBtn;

    private final String validation = "#center_column > p";


    public CartPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new Wait(driver);
    }

    public void goToCart() {
        wait.ForElement(cartBtn);
        cartBtn.click();
    }

    public void removeItem() {
        wait.ForElement(removeBtn);
        removeBtn.click();
    }

    public boolean validateCartEmpty() {
        wait.ForElement(driver.findElement(By.cssSelector(validation)));
        if (driver.findElement(By.cssSelector(validation)).getText().contains("Your shopping cart is empty.")) {
            return true;
        } else {
            return false;
        }
    }
}
