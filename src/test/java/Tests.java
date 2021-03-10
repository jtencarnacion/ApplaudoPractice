import Page.*;
import Utils.BrowserConfig;
import Utils.RandomHandler;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Tests {

    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private CartPage cartpage;

    @BeforeTest(alwaysRun = true)
    public void setUp(){
        BrowserConfig browser = new BrowserConfig();
        this.driver = browser.getDriver();
        homePage = new HomePage(driver);
        homePage.open();
        productDetailPage = new ProductDetailPage(driver);
        cartpage = new CartPage(driver);
    }

    @Test
    public void addItem_test(){
        homePage.selectItem("Printed Summer Dress");
        productDetailPage.addProduct();
        Assert.assertTrue(productDetailPage.validateProductAdded());
        Assert.assertTrue(productDetailPage.validateStoreInformation("Selenium Framework, " +
                "Research Triangle Park, North Carolina, USA", "(347) 466-7432",
                "support@seleniumframework.com"));
    }
    @Test
    public void removeItem_test() {
        homePage.selectItem("Printed Summer Dress");
        productDetailPage.addProduct();
        Assert.assertTrue(productDetailPage.validateProductAdded());
        productDetailPage.closeWindows();
        cartpage.goToCart();
        cartpage.removeItem();
        Assert.assertTrue(cartpage.validateCartEmpty());
    }
}
