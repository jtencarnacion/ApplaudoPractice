import Page.*;
import Utils.BrowserConfig;
import Utils.RandomHandler;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Tests {

    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private CartPage cartpage;
    private SearchPage searchPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        BrowserConfig browser = new BrowserConfig();
        this.driver = browser.getDriver();
        homePage = new HomePage(driver);
        homePage.open();
        productDetailPage = new ProductDetailPage(driver);
        cartpage = new CartPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test
    public void addItem_test(){
        homePage.selectItem("Printed Summer Dress");
        productDetailPage.addProduct();
        Assert.assertTrue(productDetailPage.validateProductAdded());
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

    @Test
    public void validateStoreInformation_test(){
        Assert.assertTrue(productDetailPage.getStoreInformation());
    }

    @Test
    public void searchItem_test(){
        homePage.search("blouse");
        Assert.assertTrue(searchPage.verifyItemFound());
    }

    @Test
    public void itemNotFound_test(){
        homePage.search("ItemNotFound");
        Assert.assertTrue(searchPage.verifyItemNotFound());
    }

    @AfterMethod
    public void close(){
        driver.close();
        driver.quit();
    }
}
