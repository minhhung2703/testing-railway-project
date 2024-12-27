import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.BasePage;
import railway.page.BookTicketPage;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.types.SeatType;
import railway.types.Station;
import railway.model.Ticket;
import railway.model.User;

import java.time.Duration;
import java.time.LocalDate;

public class BookTicketTest extends BasePage {
    HomePage homePage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    User user;
    Ticket ticket;

    @BeforeMethod
    public void initialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage();
        loginPage = new LoginPage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        user = new User("hung@gmail.com","123456789");
        ticket = new Ticket(
                LocalDate.now().plusDays(5),
                Station.SAI_GON,
                Station.NHA_TRANG,
                SeatType.HARD_SEAT,
                1
        );
    }

    @AfterMethod
    public void tearDown(){
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void bookTicket(){
        homePage.open();
        homePage.goToLoginPage();

        loginPage.login(user);
        homePage.goToBookTicketPage();

        bookTicketPage.bookTicket(ticket);

        Assert.assertEquals(bookTicketPage.getHeaderText(),"Ticket Booked Successfully!");

        Assert.assertEquals(bookTicketPage.getBookTicket(),ticket," Ticket Information is not match");
    }
}
