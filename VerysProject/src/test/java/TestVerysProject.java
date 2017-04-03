/**
 * Created by pgopal on 3/31/2017.
 */
import Pages.ResultsPage;
import Pages.SearchPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestVerysProject {

    WebDriver driver;

    /* Wasn't sure about the test runner requirement and how that would work. So I decided to use JUnit and parameterize some values
       to test the automation with different values. It should be the same concept.
       The order in which the variables are : Location, Pick up date, Pick up time, Drop off date, Drop off time, Company of rental car, Category of car
    */
    @Parameterized.Parameters
    public static Collection data() throws IOException {
        String[][] data = {
                //add company and category
                {"DFW", "04/03/2017", "11:30 am", "04/04/2017", "11:30 am" , "Avis", "Economy"},
                {"SFO", "05/05/2017", "11:00 am", "05/08/2017", "10:30 am", "Budget", "Compact"},
                {"JFK", "06/07/2017", "1:30 pm", "06/09/2017", "3:30 pm", "Budget", "Economy"},
                {"DEN", "07/09/2017", "9:30 am", "07/11/2017", "11:00 am", "Avis", "Fullsize"},
                {"ORD", "08/11/2017", "4:30 pm", "08/13/2017", "12:30 pm", "Avis", "Midsize"}
        };

        return Arrays.asList(data);
    }

    private String location;
    private String pickUpDate;
    private String pickUpTime;
    private String dropOffDate;
    private String dropOffTime;
    private String company;
    private String category;

    public TestVerysProject(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {

        location = s1;
        pickUpDate = s2;
        pickUpTime = s3;
        dropOffDate = s4;
        dropOffTime = s5;
        company = s6;
        category = s7;
    }


    @Before
    public void setup(){
        // ChromeDriver couldn't read from the path, so I had to set the chromedriver path here. Firefox Driver was giving some issues as well.
        // In an ideal situation, you would have been able to use whichever driver you wanted to in the initializing process.

        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.aa.com/car");
    }

    @Test
    public void testSearchPage(){
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(driver.findElement(By.id("book-panel")).isDisplayed());
        ResultsPage resultsPage = searchPage.goToSearchResults(location, pickUpDate, pickUpTime, dropOffDate, dropOffTime);
        Assert.assertEquals(driver.findElement(By.id("carHomeSearchForm.pickUpPlace")).getAttribute("value"), location);
        Assert.assertEquals(driver.findElement(By.id("carHomeSearchForm.pickUpDate")).getAttribute("value"), pickUpDate);
        Assert.assertEquals(driver.findElement(By.id("carHomeSearchForm.dropOffDate")).getAttribute("value"), dropOffDate);

        resultsPage.selectCompany(company);
        resultsPage.selectCategory(category);

        Assert.assertTrue(!driver.findElements(By.id("searchResults")).isEmpty());
        List<WebElement> companyList = driver.findElements(By.cssSelector(".customComponent.filterCheckbox-fullwidth"));
        for(WebElement e : companyList){
            if(e.findElement(By.name("filters")).getAttribute("value").equals(company.toLowerCase())){
                Assert.assertTrue(e.findElement(By.name("filters")).isSelected());
            }
            if(e.findElement(By.name("filters")).getAttribute("value").equals(category.toLowerCase())){
                Assert.assertTrue(e.findElement(By.name("filters")).isSelected());
            }
        }

        //unfinished min and max price method
        //resultsPage.setMinMaxPrice(100, 400);


        driver.quit();
    }

}
