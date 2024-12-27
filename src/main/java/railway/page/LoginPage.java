package railway.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import railway.constant.Constant;
import railway.model.User;
import railway.utils.DriverUntils;

public class LoginPage extends BasePage{
    protected WebElement element;
    private By emailTextBox = By.id("username");
    private By passwordTextBox = By.id("password");
    private By loginButtonLocator = By.xpath("//input[@title='Login']");

    // Providing Links
    private By registerLink = By.xpath("//a[normalize-space()='Registration Page']");
    private By forgotPasswordLink = By.xpath("//a[normalize-space()='Forgot Password page']");

    // Error Label
    private By errorLoginForm = By.xpath("//p[@class='message error LoginForm']");
    private By errorEmailOutput = By.xpath("//label[normalize-space()='You must specify a username.']");
    private By errorPasswordOutput = By.xpath("//label[normalize-space()='You must specify a password.']");
    // Color
    public final Color RED_COLOR = Color.fromString("#be3e16");

    public void login(User user){
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        DriverUntils.scrollIntoView(getLoginButton());
        clickLoginButton();
    }

    public void enterEmail(String email){
        Constant.WEBDRIVER.findElement(emailTextBox).sendKeys(email);
    }

    public WebElement getLoginButton(){
        return Constant.WEBDRIVER.findElement(loginButtonLocator);
    }

    public void enterPassword(String password){
        Constant.WEBDRIVER.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLoginButton(){
        getLoginButton().click();
    }

    public WebElement getRegisterLink(){
        return Constant.WEBDRIVER.findElement(registerLink);
    }

    public WebElement getForgotLink(){
        return Constant.WEBDRIVER.findElement(forgotPasswordLink);
    }

    public void goToRegisterLink(){
        DriverUntils.scrollIntoView(getRegisterLink());
        getRegisterLink().click();
    }

    public void goToForgotLink(){
        DriverUntils.scrollIntoView(getForgotLink());
        getForgotLink().click();
    }

    public WebElement getLabelError(){
        return Constant.WEBDRIVER.findElement(errorLoginForm);
    }

    public Color errorLoginMessage(){
        return Color.fromString(getLabelError().getCssValue("color"));
    }

    public WebElement errorEmailOutput(){
        return Constant.WEBDRIVER.findElement(errorEmailOutput);
    }

    public Color getErrorEmailMessage(){
        return Color.fromString(errorEmailOutput().getCssValue("color"));
    }

    public WebElement errorPasswordOutput(){
        return Constant.WEBDRIVER.findElement(errorPasswordOutput);
    }

    public Color getErrorPasswordMessage(){
        return Color.fromString(errorPasswordOutput().getCssValue("color"));
    }

}
