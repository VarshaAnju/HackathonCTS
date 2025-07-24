package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.Actionutil;
import io.cucumber.java.bs.Ali;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ItemSearchPage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='cds-react-aria-:Rhlbdj2tacqkqikta:']")
    private WebElement filterByElement;

    // click show more for certain category
    // //*[@data-testid="search-filter-group-Language"]//span[@class="cds-button-label"]

    //get text for certain category
    // //*[@data-testid="search-filter-group-Language"]//div[@class="css-1xi2dvh"]


    public ItemSearchPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFilterBy() {
        filterByElement.click();
       /* String actualTitle = driver.getTitle();
        String expectedTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);*/
    }

    public void selectCategoryAndClickSHowMore(String category) {
        WebElement categoryElement = driver.findElement(By.xpath("//*[@data-testid='search-filter-group-" + category + "']//span[@class='cds-button-label']"));
        Actionutil.scrollToElementAction(driver, categoryElement);
        categoryElement.click();
    }

    public void selectFilter(String category, String name) {
        List<WebElement> filterNameElements = driver.findElements(By.xpath("//*[@data-testid='search-filter-group-" + category + "']//div[@class='css-1xi2dvh']"));
        int size = filterNameElements.size();
        for (int i = 0; i < size; i++) {
            WebElement element = filterNameElements.get(i);
            if (element.getText().equalsIgnoreCase(name)) {
                element.click();
                break;
            }
        }

    }

}