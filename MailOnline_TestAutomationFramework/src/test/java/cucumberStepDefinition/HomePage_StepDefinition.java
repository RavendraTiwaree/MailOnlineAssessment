package cucumberStepDefinition;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import testData.testDataGeneral;
import utilities.browserDriverFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class HomePage_StepDefinition {

    browserDriverFactory bdf;
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;

    public static String baseURI = "https://localhost";
    Response response;

    @Before
    public void SetUpBrowserSession(Scenario scenario){
        bdf = new browserDriverFactory(testDataGeneral.browserName);
        driver = bdf.createLocalWebDriverSession();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait= new WebDriverWait(driver,Duration.ofSeconds(20));

        //list of page objects
        homePage = new HomePage(driver, wait);
    }

//    @After
//    public void closeBrowser (){
//        driver.quit();
//    }

    @When("User Launch the Application")
    public void launchTheApp(){
        driver.get(testDataGeneral.applicationUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage.clickGotItButton();
        homePage.verifyDateDisplayedAsCurrentDate();
    }

    @Then("User verify the webpage displays current date and time")
    public void verifyDateDisplayedAsCurrentDate(){
        homePage.verifyDateDisplayedAsCurrentDate();
    }

    @Then("User navigate to the Sport menu")
    public void navigateToSportMenu(){
        homePage.clickSportMenu();
    }


    @Then("User Verify that primary navigation colour (Sport) and secondary navigation (Football) are same")
    public void validateColourBetweenSportAndFootBall(){
        homePage.validateColourBetweenSportAndFootBall();
    }

    @Then("User click on the Football sub navigation item")
    public void clickFootBallSubMenu(){
        homePage.clickFootBallSubMenu();
    }

    @Then("User click the first article displayed and traverse to the gallery image")
    public void clickOnFirstArticle(){
        homePage.clickFirstLinkUnderSports();
    }


    @Then("User click on view gallery icon")
    public void clickOnGalleryIcon() throws InterruptedException {
        homePage.clickOnGalleryIcon();
    }

    @Then("User verify the has previous and next arrows buttons")
    public void verifyPreviousAndNextButton()  {
        homePage.verifyPreviousAndNextButton();
    }


    @Then("User click on the Facebook share icon")
    public void clickOnFaceBookIconLink() throws InterruptedException {
        homePage.clickOnFaceBookIconLink();
    }

    @Then("User verify it opens {string} modal dialog")
    public void verifyFaceBookTitle(String expectedTitle)  {
        homePage.verifyFaceBookTitle(expectedTitle);
    }

    @Then("User navigates to a video embedded within the article and click the full screen")
    public void ExpandMinimizeVideo() throws InterruptedException {
        homePage.ExpandMinimizeVideo();
    }

    @Then("User get the point for {string} team from Premier League Table section")
    public void getTheTeamPointsFromTable(String teamName){
        homePage.clickOnPremierLeague();
        homePage.getThePointFromTable(teamName);
    }


    /* API Step definition*/
    @Given("Get the pet store {string}")
    public void getThePetStoreUrl(String url) throws URISyntaxException {

        System.out.println("----------------------------");

        given()
                .get(url)
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @When("Add a new store in pet")
    public void addANewStoreInPet() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",33);

        response= given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .post("https://petstore.swagger.io/v2/pet");

        System.out.println("Post Method response"+ response.asString());
        System.out.println("Post Method Status Code = "+ response.statusCode());
    }


    @Then("find the pet by ID")
    public void findThePetByID() {
        response= given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/50");

        System.out.println("Get Method response"+ response.asString());
        System.out.println("Get Method Status Code = "+ response.statusCode());
    }
}
