import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.model.User;

import java.time.Duration;

public class LogoutTest {
    HomePage homePage;
    LoginPage loginPage;
    User user;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void logoutTest(){
        homePage.open();
        homePage.goToLoginPage();
        user = new User("hung@gmail.com","123456789");
        loginPage.login(user);
        Assert.assertEquals(homePage.getGreetingText(), "Welcome "+ user.getEmail());
        homePage.goToLogout();
        Assert.assertEquals(homePage.getGreetingText(),"Welcome guest!");
    }

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }

}
