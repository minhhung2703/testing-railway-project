package railway.page;

import org.openqa.selenium.By;
import railway.constant.Constant;

public class HomePage extends BasePage{
    public void open(){
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
    }
}
