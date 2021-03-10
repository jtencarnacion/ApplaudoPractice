package Page;

import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends Page{

    private WebDriver driver;
    private Wait wait;

    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addProductBtn;
    @FindBy(xpath = "//*[@title='Close window']")
    private WebElement closeWindowsBtn;

    private final String validation = "#layer_cart > div.clearfix";
    private final String addressTxt = "#block_contact_infos > div > ul > li:nth-child(1)";
    private final String phoneTxt = "#block_contact_infos > div > ul > li:nth-child(2)";
    private final String mailTxt = "#block_contact_infos > div > ul > li:nth-child(3)";

    public ProductDetailPage(final WebDriver driver){

        super(driver);
        this.driver = driver;
        wait = new Wait(driver);
    }

    public void addProduct(){
        wait.ForElement(addProductBtn);
        addProductBtn.click();
    }

    public void closeWindows(){
        wait.ForElement(closeWindowsBtn);
        closeWindowsBtn.click();
    }

    public boolean validateProductAdded() {
        wait.ForElement(driver.findElement(By.cssSelector(validation)));
        if (driver.findElement(By.cssSelector(validation)).getText().contains("Product successfully added")) {
            return true;
        }else{
            return false;
        }
    }

    public boolean validateStoreInformation(String address, String phone, String mail){
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.ForElement(driver.findElement(By.cssSelector(addressTxt)));
        if (driver.findElement(By.cssSelector(addressTxt)).getText().contains(address)&&
                driver.findElement(By.cssSelector(phoneTxt)).getText().contains(phone)&&
                driver.findElement(By.cssSelector(mailTxt)).getText().contains(mail)){
            System.out.println("Your test has been successfully");
            return true;
        }else{
            return false;
        }
    }
}
