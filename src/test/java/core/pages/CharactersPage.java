package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class CharactersPage {
    private WebDriver driver;

    public CharactersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String Url) {
        driver.get(Url);
    }

    public String getNumberOfComicsOnPage() {
        String text = driver.findElement(By.cssSelector("span[data-filtercounter='browse']")).getText();
        List list = Arrays.asList(text
                .replaceAll("[^-?0-9]+", " ")
                .trim()
                .split(" "));
        return list.get(list.size() - 1) + "";
    }

    public void quitDriver() {
        driver.quit();
    }

}
