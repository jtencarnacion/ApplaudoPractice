package Page;

import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.jws.WebService;

public class SearchPage extends Page{

    private WebDriver driver;
    private Wait wait;

    public SearchPage(final WebDriver driver){
        super(driver);
        this.driver = driver;
        wait = new Wait(driver);
    }

    public boolean verifyItemNotFound(){
        if (driver.findElement(By.cssSelector("#center_column > p")).getText().contains("No results were found")){
            return true;
        }else{
            return false;
        }
    }

    public boolean verifyItemFound(){
        wait.ForElement(driver.findElement(By.cssSelector("#center_column > h1")));
        if (driver.findElement(By.cssSelector("#center_column > div:nth-child(2) > div.sortPagiBar.clearfix > ul > li.display-title"))
                .getText().contains("View")){
            return true;
        }else{
            return false;
        }
    }
}

