package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;

/**
 * Created by pgopal on 4/2/2017.
 */
public class ResultsPage {

    WebDriver driver;

    //Didn't use @FindBy for this class as I also wanted to demonstrate how to use driver.findElement()

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCompany(String text){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)", "");
        List<WebElement> companyList = driver.findElements(By.cssSelector(".customComponent.filterCheckbox-fullwidth"));
        for(WebElement e : companyList){
            if(e.findElement(By.name("filters")).getAttribute("value").equals(text.toLowerCase())){
                e.click();
             //   System.out.println("Company clicked inside the results page is " + e.findElement(By.name("filters")).isSelected());
            }
        }
    }

    public void selectCategory(String text){
        List<WebElement> categoryList = driver.findElements(By.cssSelector(".customComponent.filterCheckbox-fullwidth"));
        for(WebElement e : categoryList){
            if(e.findElement(By.name("filters")).getAttribute("value").equals(text.toLowerCase())){
                e.click();
           //     System.out.println("Category clicked inside the results page is " + e.findElement(By.name("filters")).isSelected());
            }
        }
    }


    // unfinished methods for setting the minimum price and maximum price. Couldn't get it to work in the end. Explanation in the Readme

/*    public void setMinimumPrice(int min){
        WebElement priceSlider = driver.findElement(By.id("priceSlider"));
        Dimension sliderSize = priceSlider.getSize();
        int sliderWidth = sliderSize.getWidth();
        int xCoord = priceSlider.getLocation().getX();


        WebElement minPoint = driver.findElement(By.xpath("/html/body/div[2]/section/form/div/div[1]/section/div/div[2]/section[1]/div[2]/div/div[2]/a[1]"));
        Actions move = new Actions(driver);
        Action action = move.dragAndDropBy(minPoint, 30, 0).build();
        action.perform();
    }

    public void setMinMaxPrice(int min, int max){
        JavascriptExecutor ex = (JavascriptExecutor)driver;
        ex.executeScript("document.getElementById(\"priceSlider\" ).slider( \"option\", \"values\", [ " + min + ", " + max +" ] )");
    }

   public void setMaximumPrice(int i){    }

    public String getMinimumFromRange(){
        String pricing = driver.findElement(By.id("priceFilterAmounts")).getText();
        System.out.println(pricing);
        int temp = 0;
        for(int i = 0; i < pricing.length(); i++){
            if(pricing.charAt(i) == '-'){
                temp = i;
            }
        }
        return pricing.substring(1, temp);
    }

    public String getMaximumFromRange(){
        String pricing = driver.findElement(By.id("priceFilterAmounts")).getText();
        System.out.println(pricing);
        int temp = 0;
        for(int i = 0; i < pricing.length(); i++){
            if(pricing.charAt(i) == '-'){
                temp = i;
            }
        }
        return pricing.substring(temp + 3, pricing.length());
    }
  */
}
