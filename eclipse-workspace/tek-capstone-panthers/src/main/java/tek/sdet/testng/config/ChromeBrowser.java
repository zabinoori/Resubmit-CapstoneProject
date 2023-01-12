package tek.sdet.testng.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements Browser {
    @Override
    public WebDriver openBrowser(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver =  new ChromeDriver();
        driver.get(url);
        return driver;
    }
}
