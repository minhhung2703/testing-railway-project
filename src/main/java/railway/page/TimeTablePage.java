package railway.page;

import org.openqa.selenium.By;
import railway.constant.Constant;


public class TimeTablePage extends BasePage{

    private int getColumnIndex(String column){
        return Constant.WEBDRIVER.findElements(By.xpath(String.format("//tr/th[text()='%s']/preceding-sibling::th",column))).size()+1;
    }

    private String getColumnText(String column){
        return Constant.WEBDRIVER.findElement(By.xpath(String.format("//tr/td[%d]",getColumnIndex(column)))).getText();
    }

}
