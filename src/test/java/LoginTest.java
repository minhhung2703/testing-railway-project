import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.model.User;

import java.time.Duration;

public class LoginTest {
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

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void checklinksLogin() {
            homePage.open();
            homePage.goToLoginPage();
            loginPage.goToRegisterLink();
            Constant.WEBDRIVER.navigate().back();
            loginPage.goToForgotLink();
    }

    @Test
    public void testLoginWithoutfields() {
        homePage.open();
        homePage.goToLoginPage();

        user = new User("","");
        loginPage.login(user);
        // Check Text Login Form
        Assert.assertEquals(loginPage.getLabelError().getText(),"There was a problem with your login and/or errors exist in your form.");
        // Check Text Form Color
        Assert.assertEquals(loginPage.errorLoginMessage(),loginPage.RED_COLOR);
        // Check Email Text & Color
        Assert.assertEquals(loginPage.errorEmailOutput().getText(),"You must specify a username.");
        Assert.assertEquals(loginPage.getErrorEmailMessage(), loginPage.RED_COLOR);
        // Check Password Text & Color
        Assert.assertEquals(loginPage.errorPasswordOutput().getText(),"You must specify a password.");
        Assert.assertEquals(loginPage.getErrorPasswordMessage(),loginPage.RED_COLOR);
    }

    @Test
    public void validLogin() throws InterruptedException{
        homePage.open();
        homePage.goToLoginPage();

        user = new User("hung@gmail.com","123456789");
        loginPage.login(user);
        Assert.assertEquals(homePage.getGreetingText(), "Welcome "+ user.getEmail());
    }

    @Test
    public void TestLoginWithEmailOnly() {
        homePage.open();
        homePage.goToLoginPage();
        user = new User("gẻuigerugberg","");
        loginPage.login(user);
        // Check Text & Text Color
        Assert.assertEquals(loginPage.getLabelError().getText(),"There was a problem with your login and/or errors exist in your form.");
        Assert.assertEquals(loginPage.errorLoginMessage(),loginPage.RED_COLOR);
        // Bugs: Don't display the text
        Assert.assertEquals(loginPage.errorEmailOutput().getText(),"You must specify a username.");
        Assert.assertEquals(loginPage.getErrorEmailMessage(),loginPage.RED_COLOR);

        Assert.assertEquals(loginPage.errorPasswordOutput().getText(),"You must specify a password.");
        Assert.assertEquals(loginPage.getErrorPasswordMessage(),loginPage.RED_COLOR);
    }

    @Test
    public void TestLoginWithPasswordOnly() {
        homePage.open();
        homePage.goToLoginPage();
        user = new User("","13516549414");
        loginPage.login(user);
        // Check Text & Text Color
        Assert.assertEquals(loginPage.getLabelError().getText(),"There was a problem with your login and/or errors exist in your form.");
        Assert.assertEquals(loginPage.errorLoginMessage(),loginPage.RED_COLOR);

        Assert.assertEquals(loginPage.errorEmailOutput().getText(),"You must specify a username.");
        Assert.assertEquals(loginPage.getErrorEmailMessage(),loginPage.RED_COLOR);
        // Bugs: Don't display the text
        Assert.assertEquals(loginPage.errorPasswordOutput().getText(),"You must specify a password.");
        Assert.assertEquals(loginPage.getErrorPasswordMessage(),loginPage.RED_COLOR);
    }

    @Test
    public void invalidAccount() {
        homePage.open();
        homePage.goToLoginPage();

        user = new User("rauuu","1234567891");
        loginPage.login(user);
        //Check Text & Text Color Form Login
        Assert.assertEquals(loginPage.getLabelError().getText(),"Invalid username or password. Please try again.");
        Assert.assertEquals(loginPage.errorLoginMessage(),loginPage.RED_COLOR);
    }
}
