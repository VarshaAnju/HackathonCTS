package com.cts.hackathonproject.testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DebugClass {
    WebDriver driver;

    @FindBy(id="search-autocomplete-input")
    WebElement searchbox;

    @FindBy(xpath="//button[@class=\"nostyle search-button\"]")
            WebElement searchbutton;
    @FindBy(xpath="//button[@data-track-component=\"expand_filter_items_button_language\"]//span[@class=\"cds-button-label\"]")
            WebElement languagesShowMore;
    @FindBy(xpath="")
            WebElement showmore;



    @BeforeMethod
    public void preSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.get("https://www.coursera.org/");
        PageFactory.initElements(driver,this);

    }

    @Test
    public void scenario2(){
        searchbox.sendKeys("Language Learning");
        Actions a = new Actions(driver);
        a.sendKeys(Keys.ENTER).perform();

        a.scrollToElement(languagesShowMore).perform();
        a.click(languagesShowMore).perform();

        List<WebElement> languages = driver.findElements(By.xpath("//div[contains(@data-testid,'language:')]"));
        for(int i=0;i<languages.size();i++){
            WebElement lang = languages.get(i);
            System.out.println(lang.getText());
        }
    }





}
