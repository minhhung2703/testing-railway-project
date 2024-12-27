import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.ChangePasswordPage;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.model.ChangePassword;
import railway.model.User;

import java.time.Duration;

public class ChangePasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;
    User user;
    ChangePassword changePassword;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage();
        loginPage = new LoginPage();
        changePasswordPage = new ChangePasswordPage();
        user = new User("hung@gmail.com","123456789");

        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();

        Assert.assertEquals(changePasswordPage.getHeader(),"Change password");
    }

//    @AfterMethod
//    public void tearDown(){
//        Constant.WEBDRIVER.quit();
//    }

    @Test
    public void changePassword (){
        changePassword = new ChangePassword("123456789","hung123456789","hung123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getSuccessMessage().getText(),"Your password has been updated!");
        Assert.assertEquals(changePasswordPage.getSuccessPasswordMessage(),changePasswordPage.GREEN_COLOR);
    }

    @Test
    public void invalidChangePassword (){
        changePassword = new ChangePassword("123465789123456789","hung123456789","hung123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void changePasswordWithoutFields(){
        changePassword = new ChangePassword("","","");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void changePasswordWithCurrentPass(){
        changePassword = new ChangePassword("","","");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }


    @Test
    public void changePasswordWithNewPass(){
        changePassword = new ChangePassword("","","");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testNewPassUsingSpacing(){
        changePassword = new ChangePassword("123456789",   "hung 123456789","hung 123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testConfirmPassUsingSpacing(){
        changePassword = new ChangePassword("123456789",   "hung 123456789","hung 123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testConfirmPassMatchNewPass(){
        changePassword = new ChangePassword("123456789",   "hung123456789","hung123123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testNewPasswordNotSameAsCurrent(){
        changePassword = new ChangePassword("123456789",   "123456789","123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testNewPassLengthMoreThan64(){
        changePassword = new ChangePassword("123456789",   "123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789","123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }

    @Test
    public void testNewPassLengthLessThan8(){
        changePassword = new ChangePassword("123456789",   "123","123");
        changePasswordPage.changePassword(changePassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage().getText(),"Password change failed. Please correct the errors and try again.");
        Assert.assertEquals(changePasswordPage.getErrorPasswordMessage(),changePasswordPage.RED_COLOR);
    }
}
