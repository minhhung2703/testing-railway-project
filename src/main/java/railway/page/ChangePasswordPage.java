package railway.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import railway.constant.Constant;
import railway.model.ChangePassword;
import railway.utils.DriverUntils;

public class ChangePasswordPage extends BasePage{
    private By currentPasswordTextBox = By.id("currentPassword");
    private By newPasswordTextBox = By.id("newPassword");
    private By confirmPasswordTextBox = By.id("confirmPassword");
    private By changePasswordButtonLocator = By.xpath("//input[@title='Change password']");

    private By textErrorMessage = By.cssSelector(".message.error");
    private By textSuccessMessage = By.cssSelector(".message.success");
    public final Color RED_COLOR = Color.fromString("#be3e16");
    public final Color GREEN_COLOR = Color.fromString("#4f8a10");

    public void changePassword(ChangePassword changePasswordModel){
        enterCurrentPassword(changePasswordModel.getCurrentPassword());
        enterNewPassword(changePasswordModel.getNewPassword());
        enterConfirmPassword(changePasswordModel.getConfirmPassword());
        DriverUntils.scrollIntoView(getChangePasswordButton());
        clickChangePasswordButton();
    }

    public void enterCurrentPassword(String currentPassword){
        Constant.WEBDRIVER.findElement(currentPasswordTextBox).sendKeys(currentPassword);
    }

    public void enterNewPassword(String newPassword){
        Constant.WEBDRIVER.findElement(newPasswordTextBox).sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword){
        Constant.WEBDRIVER.findElement(confirmPasswordTextBox).sendKeys(confirmPassword);
    }

    public WebElement getChangePasswordButton(){
        return Constant.WEBDRIVER.findElement(changePasswordButtonLocator);
    }

    public void clickChangePasswordButton(){
        getChangePasswordButton().click();
    }

    public WebElement getSuccessMessage(){
        return Constant.WEBDRIVER.findElement(textSuccessMessage);
    }

    public Color getSuccessPasswordMessage(){
        return Color.fromString(getSuccessMessage().getCssValue("color"));
    }

    public WebElement getErrorMessage(){
        return Constant.WEBDRIVER.findElement(textErrorMessage);
    }

    public Color getErrorPasswordMessage(){
        return Color.fromString(getErrorMessage().getCssValue("color"));
    }

}
