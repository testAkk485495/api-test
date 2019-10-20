package core.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverBuilder {

    public static WebDriver createWebDriver(){

        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",path+"/src/test/resources/chrome-driver.exe");
        return new ChromeDriver();
    }
}
