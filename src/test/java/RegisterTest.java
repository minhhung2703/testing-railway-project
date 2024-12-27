import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.page.RegisterPage;
import railway.model.User_SignUp;

public class RegisterTest {
    HomePage homePage;
    User_SignUp userSignUp;
    RegisterPage registerPage;
    LoginPage loginPage;

    Faker faker;
    String password;
    String pid;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
//        Constant.WEBDRIVER.manage().window().maximize();
//        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        homePage = new HomePage();
//        registerPage = new RegisterPage();
//
//        faker = new Faker();
//        password=faker.numerify("##########");
//        registerPage = new RegisterPage(faker.internet().emailAddress(),password,password,pid);
    }

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }

//    @Test
//    public void VerifyUserCanRegisterSuccessfully(){
//        homePage.open();
//        homePage.goToRegisterPage();
//        registerPage.register(registerForm);
//        Assert.assertEquals(registerPage.getSignUpSuccessfulMessage());
//        registerPage.goToLoginPage();
//        loginPage.login(registerPage.toUser());
//        Assert.assertEquals(homePage.getGreetingText()," Welcome " + userSignUp.getEmail());
//    }

    @Test
    public void testLinksRegisterPage() {
        homePage.open();
        homePage.goToRegisterPage();
        registerPage.goToLoginPage();
        Constant.WEBDRIVER.navigate().back();
        registerPage.goToConfirmAccountPage();
    }
    @Test
    public void validAccount() {
        homePage.open();
        homePage.goToRegisterPage();

        userSignUp = new User_SignUp("hoang@gmail.com","123456789","123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getSignUpSuccessfulMessage(),"You're here");
    }
    @Test
    public void existAccountValidation() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("hoang@gmail.com","123456789","123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"This email address is already in use.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void invalidAccount() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("hoangggg","0123456789","0123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        //Check the text fields and text color
        Assert.assertEquals(registerPage.errorEmailOutput2().getText(),"Invalid email address");
        Assert.assertEquals(registerPage.errorEmailMessage2(),registerPage.RED_COLOR);
//        Assert.assertEquals(registerPage.getSignUpSuccessfulMessage(),"You're here");
    }
    @Test
    public void testRegisterWithoutFields() {
        homePage.open();
        homePage.goToRegisterPage();

        userSignUp = new User_SignUp("","","","");
        registerPage.register(userSignUp);
        //Check the label text and text color
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        //Check the text fields and text color
        Assert.assertEquals(registerPage.errorEmailOutput().getText(),"Invalid email length");
        Assert.assertEquals(registerPage.errorEmailMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPasswordOutput().getText(),"Invalid password length");
        Assert.assertEquals(registerPage.errorPasswordMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testFieldsByUsingSpacing() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("     @gmail.com","           ","           ","           ");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorEmailOutput2().getText(),"Invalid email address");
        Assert.assertEquals(registerPage.errorEmailMessage2(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPasswordOutput().getText(),"Invalid password length");
        Assert.assertEquals(registerPage.errorPasswordMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testEmailUsingSpacing() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp =  new User_SignUp("       @gmail.com","123456789","123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);

        Assert.assertEquals(registerPage.errorEmailOutput2().getText(),"Invalid email address");
        Assert.assertEquals(registerPage.errorEmailMessage2(),registerPage.RED_COLOR);
    }
    @Test
    public void testPassWordUsingSpacing(){
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp =  new User_SignUp("khanh123@gmail.com","123456   789","123456   789","123456789");
        registerPage.register(userSignUp);
        // Bugs
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPasswordOutput().getText(),"Invalid password length");
        Assert.assertEquals(registerPage.errorPasswordMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testConfirmPasswordUsingSpacing() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp =  new User_SignUp("khanh123456@gmail.com","123456789","123456   789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorConfirmPasswordOutput().getText(),"The two passwords do not match");
        Assert.assertEquals(registerPage.errorConfirmPasswordMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPassportUsingSpacing() {
        homePage.open();
        homePage.goToRegisterPage();
        // Bugs: User can not using spacing into the passport field
        userSignUp =  new User_SignUp("khanh123@gmail.com","123456789","123456789","123456   789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testEmailWithCharacters() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("hoang#$#@gmail.com","0123456789","0123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorEmailOutput().getText(),"Invalid email length");
        Assert.assertEquals(registerPage.errorEmailMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testEmailLengthLessThan6() {
        homePage.open();
        homePage.goToRegisterPage();
        // Less than 6 characters
        userSignUp = new User_SignUp("m@gmail","123456789","123456789","123456789");
        registerPage.register(userSignUp);
        //Check the label text and text color
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        //Check the text fields and text color
        Assert.assertEquals(registerPage.errorEmailOutput2().getText(),"Invalid email address");
        Assert.assertEquals(registerPage.errorEmailMessage2(),registerPage.RED_COLOR);
    }
    @Test
    public void testEmailLengthMoreThan32() {
        homePage.open();
        homePage.goToRegisterPage();
        // More than 32 characters
        userSignUp = new User_SignUp("nguyenminhhungnguyenminhhungnguyenminhhungnguyenminhhung@gmail.com","123456789","123456789","123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorEmailOutput().getText(),"Invalid email length");
        Assert.assertEquals(registerPage.errorEmailMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPasswordLengthLessThan8() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("minhhung@gmail.com","123","123","123456789");
        registerPage.register(userSignUp);

        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPasswordOutput().getText(),"Invalid password length");
        Assert.assertEquals(registerPage.errorPasswordMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPasswordLengthMoreThan64() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("minhhung@gmail.com","123456789123456789123456789123456789123456789123456789123456789123456789123456789","123456789123456789123456789123456789123456789123456789123456789123456789123456789","123456789");
        registerPage.register(userSignUp);

        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPasswordOutput().getText(),"Invalid password length");
        Assert.assertEquals(registerPage.errorPasswordMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testConfirmPasswordMatchPassword() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("minhhung@gmail.com","123456789","0123456789","123456789");
        registerPage.register(userSignUp);

        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorConfirmPasswordOutput().getText(),"The two passwords do not match");
        Assert.assertEquals(registerPage.errorConfirmPasswordMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPassportLessThan8() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("minhhung@gmail.com","123456789","0123456789","12");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPassportMoreThan20() {
        homePage.open();
        homePage.goToRegisterPage();
        userSignUp = new User_SignUp("minhhung@gmail.com","123456789","0123456789","123456789123456789123456789");
        registerPage.register(userSignUp);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
    @Test
    public void testPassportWithoutNumber() {
        homePage.open();
        homePage.goToRegisterPage();
        // Bugs Register successfully: User can not register without number
        userSignUp = new User_SignUp("hoanggg@gmail.com","123456789","123456789","iuefuwehierhg");
        registerPage.register(userSignUp);
        //Check the label text and text color
        Assert.assertEquals(registerPage.getLabelError().getText(),"There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(registerPage.errorRegisterMessage(),registerPage.RED_COLOR);
        Assert.assertEquals(registerPage.errorPassportOutput().getText(),"Invalid ID length");
        Assert.assertEquals(registerPage.errorPassportMessage(),registerPage.RED_COLOR);
    }
}
