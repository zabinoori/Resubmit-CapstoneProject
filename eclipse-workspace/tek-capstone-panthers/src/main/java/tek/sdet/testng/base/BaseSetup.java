package tek.sdet.testng.base;

import static tek.sdet.testng.utilities.FileUtilities.getFileInputSteam;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

import tek.sdet.testng.config.Browser;
import tek.sdet.testng.config.ChromeBrowser;
import tek.sdet.testng.config.FireFoxBrowser;
import tek.sdet.testng.utilities.DatabaseConnectionUtility;


public class BaseSetup {
    private static WebDriver driver;
    private DatabaseConnectionUtility dbUtility;

    public WebDriver getDriver() {
        return driver;
    }

    public DatabaseConnectionUtility getDbUtility() {
        return this.dbUtility;
    }

    private Properties loadProperty() {
        try {
            Properties properties = new Properties();
            String filePath = System.getProperty("user.dir") + "/src/main/resources/config/test_config.properties";
            properties.load(getFileInputSteam(filePath));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Source File not found with message " + e.getMessage());
        }
    }

    private String getProperty(String propertyKey) {
        return loadProperty().getProperty(propertyKey);
    }


    public void setupBrowser() {
        String browserType = this.getProperty("browser");
        String url = this.getProperty("url");
        if (browserType.equalsIgnoreCase("chrome")) {
            Browser browser = new ChromeBrowser();
            this.driver = browser.openBrowser(url);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            Browser browser = new FireFoxBrowser();
            this.driver = browser.openBrowser(url);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
    }


    public void quiteBrowser() {
        if (this.getDriver() != null)
            this.getDriver().quit();
        ;
    }

    public void getConnectedToDatabase() {
        this.dbUtility = new DatabaseConnectionUtility(
                getProperty("db_url"),
                getProperty("db_username"),
                getProperty("db_password")
        );
    }


}
