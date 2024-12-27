package railway.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import railway.constant.Constant;

public class DriverUntils {
    public static void scrollIntoView(WebElement element){
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView()",element);
    }
}
