# VerysProject
Automation Project for Verys

This is the automation project for verys. The objective is copy pasted below -

Objective - 
1.	Go to https://www.aa.com/car
2.	Fill out the book car form. Structure your code such that a test runner should be able to specify the following values:
  a.	Pick-up location
  b.	Number of days from the current date for the pick-up date
  c.	Pick-up time
  d.	Number of days from the pick-up date for the drop-off date
  e.	Drop-off time
3.	On the “Your search results” page it should select a car of a specified category and company (again, this should be structured such that a test runner could input these values)
4.	Present results in human readable format
5.	Bonus: On the “Your search results” page set the minimum and maximum price (both able to be specified by the test runner)


How to run -

Download the repository into a folder and open the project in an IDE. Go to TestVerysProject, located under the test folder which is inside
the src folder and run it. You should probably change the path of chromedriver in line 74 of TestVerysProject.java to reflect where it 
is in your machine.



Structure -

I tried to follow best practices in automation by using POM(Page Object Model). In this model, we isolate the pages into their specific
classes and these classes store the WebElements and the actions that can be done on those elements. Since this test would take two pages, I
made two classes, the search page(the landing page) and the results page, and stored their respective elements in them. I wanted to use both
driver.findElement() and the @FindBy functionality to demostrate how they can be used to get the elements. Thats why SearchPage's elements
are predominantly found using @FindBy and ResultsPage by driver.FindElement().

I wasn't really experienced with a test runner for testing multiple values, So I used JUnit to parameterize some values and decided to run
those values for the test. Since it is a JUnit test, there are some assertions to check if certain conditions are being met.
I feel like I did structure the files and folders incorrectly. The pages folder with the pages classes should have been under the main src
folder and not the test folder. Other than that it should be structured properly.



Problems I faced -

The immediate problem I faced was FireFoxDriver and ChromeDriver not working properly. The Firefox one was starting a new browser, but 
wasn't getting the url. Tried downgrading my Firefox version, but it still did not work, so I tried using ChromeDriver. ChromeDriver wasn't
getting set in my path in environment variables, so I had to set the System Property before I instantiated the driver. I feel this is bad 
practice as no other machine would have the driver in the folder I specified. You could change the path of chromedriver in line 74 of
TestVerysProject.java. This might not be ideal since we should test in every possible browser driver, but due to time constraints, I had 
to resort to using ChromeDriver.

I did face some issues using ChromeDriver. I wasn't able to click on certain elements in the page for some reason, so I had to use 
JavaScriptExecutor class to trigger a click. This is shown in the way I went about clicking the search button in the Search Page. I also 
had to use it to scroll down on the Results page as ChromeDriver wasn't picking up the car rental company and car category checkbox 
elements.

I also couldn't finish the bonus objective. I felt like I was close to making a breakthrough, but realized I was spending more time than 
I would have liked on it and decided to focus on the main objectives. I still have that portion of the code commented on ResultsPage. I 
managed to automate moving the slider, but spent too much time trying to figure out how to check the minimum and maximum values after 
the slider has moved. The way I wanted to work on it was get the min and max value, get the min slider element and move it across, while 
constantly checking the updated min value with the user given min value. When both those values are equal, stop the slide animation and 
repeat the process for max value. 
If it weren't for the time constraints, I feel I could have accomplished the bonus objective
