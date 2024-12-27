package railway.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import railway.constant.Constant;
import railway.utils.DriverUntils;

public class BasePage {
    private By loginTabLocator = By.xpath("//span[contains(text(),'Login')]");
    private By registerTabLocator = By.xpath("//span[normalize-space()='Register']");
    private By greetingLabelLocator = By.cssSelector("div.account strong");
    private By someeLocatorLink = By.xpath("//body//center//a[normalize-space()='Web hosting by Somee.com']");
    private By bookTicketLocator = By.xpath("//span[normalize-space()='Book ticket']");
    private By myTicketLocator = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private By contactLocator = By.xpath("//span[contains(text(),'Contact')]");
    private By timeTableLocator = By.xpath("//span[contains(text(),'Timetable')]");
    private By ticketPriceLocator = By.xpath("//span[contains(text(),'Ticket price')]");
    private By logoutButtonLocator = By.xpath("//a[@href='/Account/Logout']");
    private By faqLocator = By.xpath("//span[contains(text(),'Contact')]");
    private By changePasswordLocator = By.xpath("//span[contains(text(),'Change password')]");
    private By headerLocator = By.tagName("h1");


    public void goToLoginPage(){
        Constant.WEBDRIVER.findElement(loginTabLocator).click();
    }

    public void goToLogout(){
        Constant.WEBDRIVER.findElement(logoutButtonLocator).click();
    }

    public void goToRegisterPage(){
        Constant.WEBDRIVER.findElement(registerTabLocator).click();
    }

    public String getHeader(){
        return Constant.WEBDRIVER.findElement(headerLocator).getText();
    }

    public String getGreetingText(){
        return Constant.WEBDRIVER.findElement(greetingLabelLocator).getText();
    }

    public void goToSomeePage(){
        DriverUntils.scrollIntoView(getSomeePage());
        clickSomeePageButton();
    }

    public WebElement getSomeePage(){
       return Constant.WEBDRIVER.findElement(someeLocatorLink);
    }

    public void clickSomeePageButton(){
       getSomeePage().click();
    }

    public void goToFAQPage(){
        Constant.WEBDRIVER.findElement(faqLocator).click();
    }

    public void goToContactPage(){
        Constant.WEBDRIVER.findElement(contactLocator).click();
    }

    public void goToTimeTablePage(){
        Constant.WEBDRIVER.findElement(timeTableLocator).click();
    }

    public void goToTicketPricePage(){
        Constant.WEBDRIVER.findElement(ticketPriceLocator).click();
    }

    public void goToMyTicketPage(){
        Constant.WEBDRIVER.findElement(myTicketLocator).click();
    }

    public void goToBookTicketPage(){
        Constant.WEBDRIVER.findElement(bookTicketLocator).click();
    }

    public void goToChangePasswordPage(){
        Constant.WEBDRIVER.findElement(changePasswordLocator).click();
    }
}
