package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by pgopal on 4/2/2017.
 */
public class SearchPage {

    WebDriver driver;
    //using FindBy to locate the elements
    @FindBy(id = "carHomeSearchForm.pickUpPlace")
    WebElement pickUpLocation;

    @FindBy(id = "carHomeSearchForm.pickUpDate")
    WebElement pickUpDate;

    @FindBy(id = "pickUpTime")
    WebElement pickUpTime;

    @FindBy(id = "carHomeSearchForm.dropOffDate")
    WebElement dropOffDate;

    @FindBy(id = "dropOffTime")
    WebElement dropOffTime;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Defining all the user actions that can be performed in the form for readability

    public void typePickUpLocation(String text){
        pickUpLocation.sendKeys(text);
    }

    public void typePickUpDate(String text){
        pickUpDate.sendKeys(text);
    }

    public void selectPickUpTime(String text){
        Select select = new Select(pickUpTime);
        select.selectByVisibleText(text);
    }

    public void typeDropOffDate(String text){
        dropOffDate.sendKeys(text);
    }

    public void selectDropOffTime(String text){
        Select select = new Select(dropOffTime);
        select.selectByVisibleText(text);
    }

    public ResultsPage clickSearch(){
        //chrome driver was throwing errors saying that it couldn't find the element to click. So I used a JavascriptExecutor to work around it
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click();", driver.findElement(By.id("searchButton")));
        return new ResultsPage(driver);
    }

    public ResultsPage goToSearchResults(String location, String pickDate, String pickTime, String dropDate, String dropTime){
        typePickUpLocation(location);
        typePickUpDate(pickDate);
        selectPickUpTime(pickTime);
        typeDropOffDate(dropDate);
        selectDropOffTime(dropTime);
        return clickSearch();
    }

}
