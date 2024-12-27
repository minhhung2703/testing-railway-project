import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.BasePage;
import railway.page.ContactPage;
import railway.page.HomePage;

import java.time.Duration;

public class ContactTest{
    HomePage homePage;
    ContactPage contactPage;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage();
        contactPage = new ContactPage();
    }

    @Test
    public void contactInfo(){
        homePage.open();
        homePage.goToContactPage();
        Assert.assertEquals(contactPage.getHeader(),"Contact Information","Contact Page header is incorrect!");
    }

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }
}
