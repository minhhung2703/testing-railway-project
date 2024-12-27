package railway.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import railway.constant.Constant;
import railway.model.FilterTicket;
import railway.types.Station;
import railway.types.Status;
import railway.utils.DriverUntils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyTicketPage extends BasePage{
    By selectDepartLocator = By.name("FilterDpStation");
    By selectArriveLocator = By.name("FilterArStation");
    By inputDateLocator = By.name("FilterDpDate");
    By selectStatusLocator = By.name("FilterStatus");
    By filterButtonLocator = By.xpath("//input[@value='Apply Filter']");

    private final By tableLocator = By.xpath("//table[@class='MyTable']/tbody/tr");

    public void cancelTicketID(int id){
        By cancelButton = By.xpath(String.format(String.format("//input[@value='Cancel'][@onclick='DeleteTicket(%d);']", id)));
        Constant.WEBDRIVER.findElement(cancelButton).click();
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();

    }

    public boolean isCancelTicketButtonIsDisplayed(int id){
        By cancelButton = By.xpath(String.format(String.format("//input[@value='Cancel'][@onclick='DeleteTicket(%d);']", id)));
        return !Constant.WEBDRIVER.findElements(cancelButton).isEmpty();
    }

    public void filterTicket(FilterTicket ticket){
        selectDepartStation(ticket.getDepartStation());
        selectArriveStation(ticket.getArriveStation());
        enterDate(ticket.getDepartDate());
        selectStatus(ticket.getStatus());
        DriverUntils.scrollIntoView(getApplyFilterButton());
        clickApplyFilterButton();
    }

    public List<FilterTicket> getfilteredTickets(){
        List<FilterTicket> tickets = new ArrayList<>();
        List<WebElement> rows = Constant.WEBDRIVER.findElements(tableLocator);
        for(WebElement row: rows){
            tickets.add(new FilterTicket(
                    getDepartStation(row),
                    getArriveStation(row),
                    getDate(row),
                    getStatus(row)
            ));
        }
        return tickets;
    }

    private Station getDepartStation(WebElement row){
        return Station.fromText(getColumnText(row,"Depart Station"));
    }

    private Station getArriveStation(WebElement row){
        return Station.fromText(getColumnText(row,"Arrive Station"));
    }

    private String getDate(WebElement row){
        String date = getColumnText(row,"Depart Date");
        return LocalDate.parse(date,Constant.FORMATTER).format(Constant.FORMATTER);
    }

    private Status getStatus(WebElement row){
        return Status.fromText(getColumnText(row,"Status"));
    }

    private String getColumnText(WebElement row, String column){
        int columnIndex = getColumnIndex(column);
        return row.findElement(By.xpath(String.format(".//tr/td[%d]",columnIndex))).getText();
    }

    private int getColumnIndex(String column){
        return Constant.WEBDRIVER.findElements(By.xpath(String.format("//tr/th[text()='%s']/preceding-sibling::th",column))).size() + 1;
    }

    private void selectDepartStation(Station station){
        Select select = new Select(Constant.WEBDRIVER.findElement(selectDepartLocator));
        if (select.getFirstSelectedOption().getText().equals(station.getText())){
            return;
        }
        select.selectByVisibleText(station.getText());
    }

    private void selectArriveStation(Station station){
        Select select = new Select(Constant.WEBDRIVER.findElement(selectArriveLocator));
        select.selectByVisibleText(station.getText());

    }

    private void selectStatus(Status status){
        Select select = new Select(Constant.WEBDRIVER.findElement(selectStatusLocator));
        select.selectByVisibleText(status.getText());
    }

    private void enterDate(String date){
        Constant.WEBDRIVER.findElement(inputDateLocator).sendKeys(String.format(date.formatted(Constant.FORMATTER)));
    }

    private WebElement getApplyFilterButton(){
        return Constant.WEBDRIVER.findElement(filterButtonLocator);
    }

    private void clickApplyFilterButton(){
        getApplyFilterButton().click();
    }

    public boolean getfilterTickets() {
        return false;
    }
}
