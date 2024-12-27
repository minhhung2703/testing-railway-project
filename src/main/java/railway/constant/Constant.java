package railway.constant;

import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;


public class Constant {
    public static WebDriver WEBDRIVER;
    public static String RAILWAY_URL = "http://railwayb2.somee.com/";
    public static final DateTimeFormatter FORMATTER= DateTimeFormatter.ofPattern("M/d/yyyy");

}
