package tek.sdet.testng.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tek.sdet.testng.base.BaseSetup;

import java.time.Duration;

public class CommonUtility extends BaseSetup {

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    public WebElement waitTillClickable(WebElement element) {
        return this.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitTillPresence(WebElement element) {
        return this.getWait().until(ExpectedConditions.visibilityOf(element));
    }


    public void click(WebElement element) {
        this.waitTillClickable(element).click();
    }

    public void sendText(WebElement element, String value) {
        this.waitTillPresence(element).sendKeys(value);
    }

    public String getElementText(WebElement element) {
        return this.waitTillPresence(element).getText();
    }
	public String getTitle() {
		String title = getDriver().getTitle();
		return title;
	}


    public String takeScreenShot() {
        String screenShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenShot;
    }
    public void selectCalendarDateWithJS(String date, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + date + "');", element);
    }
}
