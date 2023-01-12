package tek.sdet.testng.config;

import org.openqa.selenium.WebDriver;

public interface Browser {

    WebDriver openBrowser(String url);
}
