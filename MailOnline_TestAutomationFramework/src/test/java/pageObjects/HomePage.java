package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class HomePage {

    final WebDriver driver;
    final WebDriverWait wait;

    public HomePage (WebDriver driver,WebDriverWait wait ){
        super();
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);

    }

    @FindBy(how = How.XPATH, using ="//button[text()='Got it']")
    WebElement btn_GotIt;

    @FindBy(how = How.XPATH, using ="(//a[text()='Sport'])[1]")
    WebElement lnk_Sport;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Football')]")
    WebElement lnk_Football;

    @FindBy(how = How.XPATH, using ="(//div[@itemprop='itemListElement'])[1]/h2/a")
    WebElement lnk_FirstLinkUnderSport;

    @FindBy(how = How.XPATH, using ="(//button[contains(@class,'openGalleryButton')])[1]/div[2]")
    WebElement img_GalleryIcon;

    @FindBy(how = How.XPATH, using ="//div[contains(@id,'footballco-video-player-close-button')]")
    WebElement icon_CrossCloseVideo;

    @FindBy(how = How.XPATH, using ="//button[contains(@class,'nextButton')]")
    WebElement btn_Next;

    @FindBy(how = How.XPATH, using ="//button[contains(@class,'previousButton')]")
    WebElement btn_Previous;

    @FindBy(how = How.XPATH, using ="//button[contains(@class,'closeButton')]")
    WebElement btn_Close;

    @FindBy(how = How.XPATH, using ="//a[contains(text(),'Premier League')]")
    WebElement lnk_PremierLeague;


    //click on Got it Link as kind of accept cookies page
    public void clickGotItButton(){
        btn_GotIt.click();
    }


    public void verifyDateDisplayedAsCurrentDate() {
        // Retrieve the element that displays the date
        WebElement dateElement = driver.findElement(By.xpath("//div[@id='weather-wrapper']/strong"));

        // Get the date text
        String dateString = dateElement.getText().replace("th","");

        // Convert the date string to a LocalDate object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d yyyy");
         LocalDate date = LocalDate.parse(dateString, formatter);

        // Get the current date as a LocalDate object
        LocalDate now = LocalDate.now();

        // Compare the date on the web page with the current date
        if (date.isEqual(now)) {
            System.out.println("Date displayed on Mail Online web page is the current date.");
        } else {
            System.out.println("Date displayed on Mail Online web page is not the current date.");
        }
    }

    public void clickSportMenu(){
        lnk_Sport.click();
    }

    public void validateColourBetweenSportAndFootBall (){
        String sportActualColour = lnk_Sport.getCssValue("color");
        System.out.println("Color displayed in UI for Sports = " + sportActualColour);

        String footBallActualColour = lnk_Football.getCssValue("color");
        System.out.println("Color displayed in UI for Football = " + footBallActualColour);

        if (sportActualColour.equals(footBallActualColour)) {
            System.out.println("Both Text has the expected color");
        } else {
            System.out.println("Both Text does not have the expected color");
        }

    }

    public void clickFootBallSubMenu(){
        lnk_Football.click();
    }

    public void clickFirstLinkUnderSports(){
        lnk_FirstLinkUnderSport.click();
    }

    public void clickOnGalleryIcon() throws InterruptedException {
        Thread.sleep(9000);

        //close the add window if open
        if (icon_CrossCloseVideo.isDisplayed()){
            icon_CrossCloseVideo.click();
            System.out.println("clicked cross button");
        }

       By locator = By.xpath("(//button[contains(@class,'openGalleryButton')])[1]/div[2]");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);

        //click on gallery image
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    public void verifyPreviousAndNextButton (){
        if (btn_Next.isEnabled() && btn_Next.isDisplayed() ){
            btn_Next.click();
            System.out.println("clicked On Next button and moved to next page");
        }else{
            System.out.println("Do not clicked Next button");
        }

        if (btn_Previous.isEnabled() && btn_Previous.isDisplayed() ){
            btn_Previous.click();
            System.out.println("clicked On Previous button and navigated back to previous page");
        }else{
            System.out.println("Previous button not Displayed or Disable");
        }

        //close the picture slide
        btn_Close.click();
    }

  public void clickOnFaceBookIconLink () throws InterruptedException {
      Thread.sleep(9000);

      //close the add window if open
      if (icon_CrossCloseVideo.isDisplayed()){
          icon_CrossCloseVideo.click();
          System.out.println("clicked cross button");
      }



      Actions actions = new Actions(driver);

      By gallery = By.xpath("(//button[contains(@class,'openGalleryButton')])[1]/div[2]");
      wait.until(ExpectedConditions.presenceOfElementLocated(gallery));
      WebElement galleryElement = driver.findElement(gallery);
      actions.moveToElement(galleryElement).build().perform();

      //Move cursor to facebook link
      By locator = By.xpath("(//li[contains(@data-social-scope,'facebook')])[1]");
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      WebElement element = driver.findElement(locator);

      actions.moveToElement(element).click().perform();
      Thread.sleep(4000);
  }

  public void verifyFaceBookTitle(String expectedTitle){
        String parentWindow = driver.getWindowHandle();
       Set<String> allWindows =  driver.getWindowHandles();

       for (String currentWindow : allWindows) {
           driver.switchTo().window(currentWindow);
       }

      //Verify the Title
      Assert.assertEquals(driver.getTitle(), expectedTitle, "Verify the Page Title");
      System.out.println("Get the FaceBook URL " +  driver.getTitle());
      System.out.println("Get the FaceBook URL " +  driver.getCurrentUrl());


      //close current window
       driver.close();

       //switch back to parent window
       driver.switchTo().window(parentWindow);
      System.out.println("Get the title post switching to parent window " +  driver.getTitle());
  }

  public void ExpandMinimizeVideo() throws InterruptedException {
      By locator = By.xpath("//div[contains(@class,'vjs-fullscreen-control vjs-control')]");
//      By locator = By.xpath("//span[text()='Fullscreen']");
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      WebElement element = driver.findElement(locator);
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", element);
      System.out.println("Clicked on Maximize video button");
     Thread.sleep(3000);
      element.click();
      System.out.println("Clicked on Minimize video button");
  }

  public void clickOnPremierLeague (){
      By locator = By.xpath("(//a[contains(text(),'Premier League')])[1]");
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      WebElement element = driver.findElement(locator);

      //click
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", element);
  }

  public void getThePointFromTable (String teamName){
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(12));

      By position = By.xpath("(//div[contains(@class,'competitionTable')]/table/tbody/tr/td//following-sibling::td[contains(text(),'"+teamName+"')]/..//td)[1]");
      wait.until(ExpectedConditions.presenceOfElementLocated(position));
      WebElement positionOfTeam = driver.findElement(position);
      String positionOfTeamValue = positionOfTeam.getText();
      System.out.println( teamName + " Position displayed in Premier League table  = " + positionOfTeamValue);

      By locator = By.xpath("(//div[contains(@class,'competitionTable')]/table/tbody/tr/td//following-sibling::td[contains(text(),'"+teamName+"')]//following-sibling::td)[3]");
      WebElement element = driver.findElement(locator);
      String pointValue = element.getText();
      System.out.println( teamName + " Points displayed in Premier League table  = " + pointValue);
  }
}
