import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.model.User;

public class RailwayTest {
    HomePage homePage;
    LoginPage loginPage;
    User user;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        homePage = new HomePage();
        loginPage = new LoginPage();
        user = new User("hung@gmail.com","123456789");
    }

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void test_GuestStatus(){
        homePage.open();
        Assert.assertEquals(homePage.getGreetingText(),"Welcome guest!");
    }

    @Test
    public void testSelenium(){
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        Assert.assertEquals(homePage.getGreetingText(), "Welcome "+ user.getEmail());
    }

    @Test
    public void testLinksWithoutLogin(){
        homePage.open();
        homePage.goToContactPage();
        homePage.goToTimeTablePage();
        homePage.goToTicketPricePage();
        homePage.goToBookTicketPage();
        homePage.goToSomeePage();

    }

    @Test
    public void testLinksAfterLogin(){
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);

        homePage.goToContactPage();
        homePage.goToTimeTablePage();
        homePage.goToTicketPricePage();
        homePage.goToBookTicketPage();
        homePage.goToMyTicketPage();
        homePage.goToChangePasswordPage();
        homePage.goToSomeePage();

    }
}
