import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.page.BookTicketPage;
import railway.page.HomePage;
import railway.page.LoginPage;
import railway.page.MyTicketPage;
import railway.types.SeatType;
import railway.types.Station;
import railway.model.Ticket;
import railway.model.User;

import java.time.Duration;
import java.time.LocalDate;

public class CancelTicketTest {
    HomePage homePage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;
    User user;
    Ticket ticket;
    int id;

    @BeforeMethod
    public void intialize(){
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        myTicketPage = new MyTicketPage();

        user = new User("hung@gmail.com","123456789");
        ticket = new Ticket(
                LocalDate.now().plusDays(5),
                Station.SAI_GON,
                Station.NHA_TRANG,
                SeatType.HARD_SEAT,
                1
        );
        homePage.open();
        homePage.goToLoginPage();

        loginPage.login(user);
        homePage.goToBookTicketPage();

        bookTicketPage.bookTicket(ticket);
        id = bookTicketPage.getTicketID();
    }

    @Test
    public void cancelTicketTest(){
        bookTicketPage.goToMyTicketPage();
        myTicketPage.cancelTicketID(id);

        Assert.assertFalse(myTicketPage.isCancelTicketButtonIsDisplayed(id));
    }

    @AfterMethod
    public void teaDown(){
        Constant.WEBDRIVER.quit();
    }
}
