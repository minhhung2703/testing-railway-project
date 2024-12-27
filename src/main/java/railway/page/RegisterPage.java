package railway.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import railway.constant.Constant;
import railway.model.User_SignUp;
import railway.utils.DriverUntils;

public class RegisterPage extends BasePage{
    protected WebElement element;
    private By emailTextBox = By.id("email");
    private By passwordTextBox = By.id("password");
    private By confirmPasswordTextBox = By.id("confirmPassword");
    private By passportTextBox = By.id("pid");
    private By registerButtonLocator = By.xpath("//input[@title='Register']");
    private final By getSignUpText = By.xpath("//p[contains(text(),\"You're here\")]");

    // Providing link
    private By loginLink = By.xpath("//a[normalize-space()='login']");
    private By confirmAccountLink = By.xpath("//a[normalize-space()='here']");

    // Error label
    private By errorRegisterForm = By.cssSelector(".message.error");
    private By errorEmailOutput = By.xpath("//label[normalize-space()='Invalid email length']");
    private By errorEmailOutput2 = By.xpath("//label[normalize-space()='Invalid email address']");
    private By errorPasswordOutput = By.xpath("//label[normalize-space()='Invalid password length']");
    private By errorConfirmPasswordOutput = By.xpath("//label[normalize-space()='The two passwords do not match']");
    private By errorPassportOutput = By.xpath("//label[normalize-space()='Invalid ID length']");

    // Error color
    public final Color RED_COLOR = Color.fromString("#be3e16");

    public void register(User_SignUp userSignUp){
        enterEmail(userSignUp.getEmail());
        enterPassword(userSignUp.getPassword());
        enterConfirmPassword(userSignUp.getConfirmPassword());
        enterpassport(userSignUp.getPassport());
        DriverUntils.scrollIntoView(getRegisterButton());
        clickRegisterButton();
    }

    public void enterEmail(String email){
        Constant.WEBDRIVER.findElement(emailTextBox).sendKeys(email);
    }

    public void enterPassword(String password){
        Constant.WEBDRIVER.findElement(passwordTextBox).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword){
        Constant.WEBDRIVER.findElement(confirmPasswordTextBox).sendKeys(confirmPassword);
    }

    public void enterpassport(String passport){
        Constant.WEBDRIVER.findElement(passportTextBox).sendKeys(passport);
    }

    public WebElement getRegisterButton(){
        return Constant.WEBDRIVER.findElement(registerButtonLocator);
    }

    public void clickRegisterButton(){
        getRegisterButton().click();
    }

    public String getSignUpSuccessfulMessage(){
        return Constant.WEBDRIVER.findElement(getSignUpText).getText();
    }

    public void goToLoginPage (){
        Constant.WEBDRIVER.findElement(loginLink).click();
    }

    public void goToConfirmAccountPage (){
        Constant.WEBDRIVER.findElement(confirmAccountLink).click();
    }

    public WebElement getLabelError(){
        return Constant.WEBDRIVER.findElement(errorRegisterForm);
    }
    public Color errorRegisterMessage(){
        return Color.fromString(getLabelError().getCssValue("color"));
    }

    public WebElement errorEmailOutput(){
        return Constant.WEBDRIVER.findElement(errorEmailOutput);
    }
    public Color errorEmailMessage(){
        return Color.fromString(errorEmailOutput().getCssValue("color"));
    }

    public WebElement errorEmailOutput2(){
        return Constant.WEBDRIVER.findElement(errorEmailOutput2);
    }
    public Color errorEmailMessage2(){
        return Color.fromString(errorEmailOutput2().getCssValue("color"));
    }

    public WebElement errorPasswordOutput(){
        return Constant.WEBDRIVER.findElement(errorPasswordOutput);
    }
    public Color errorPasswordMessage(){
        return Color.fromString(errorPasswordOutput().getCssValue("color"));
    }

    public WebElement errorConfirmPasswordOutput(){
        return Constant.WEBDRIVER.findElement(errorConfirmPasswordOutput);
    }
    public Color errorConfirmPasswordMessage(){
        return Color.fromString(errorConfirmPasswordOutput().getCssValue("color"));
    }

    public WebElement errorPassportOutput(){
        return Constant.WEBDRIVER.findElement(errorPassportOutput);
    }
    public Color errorPassportMessage(){
        return Color.fromString(errorPassportOutput().getCssValue("color"));
    }



}
