package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Locale;

public class browserDriverFactory {

    public WebDriver driver;
    private final String browser;

    public browserDriverFactory (String browserName){
        this.browser =browserName.toLowerCase();
    }

    public WebDriver createLocalWebDriverSession (){
        switch (browser){
            case "chrome":
                System.setProperty("WebDriver.chrome.driver",System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("WebDriver.edge.driver",System.getProperty("user.dir") + "\\libs\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Please provide the correct browser name ");
        }
        return  driver;
    }

}
