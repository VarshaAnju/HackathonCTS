package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.Actionutil;
import com.cts.hackathonproject.utils.seleniumutils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v135.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LanguagesPage extends BasePage{

    private WebDriver driver;

    public LanguagesPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@class=\"cds-189 cds-formLabel-root cds-formLabel-onLight cds-formControl-formLabel css-s27fdh cds-195\"]")
    private List<WebElement> filterOptions;

    //@FindBy(xpath="(//button[@class=\"cds-171 cds-button-disableElevation cds-button-ghost css-1cct3hj\"]/span[@class=\"cds-button-label\"])")




/*
    public void getLanguagesList(String filterName){
        for(int i=0;i<filterOptions.size();i++){
            WebElement eachFilter = filterOptions.get(i);
            if(eachFilter.getText().equalsIgnoreCase(filterName)){
                JavaScriptUtil.JSscrollToElement(eachFilter,driver);
                return;
            }
        }
    }*/

    public void clickOnShowMore(String filtername){
        // (//button[@aria-label="Show more Language options"]/span[@class="cds-button-label"])
        WebElement filterShowMoreElement;
        if(filtername.equals("Language")||filtername.equals("Educator")||filtername.equals("Subtitles")||filtername.equals("Skills")){
            filterShowMoreElement = driver.findElement(By.xpath("//button[@aria-label='Show more "+filtername+" options']/span[@class='cds-button-label']"));
            Actionutil.scrollToElementAction(driver,filterShowMoreElement);
            Actionutil.clickOnElement(driver,filterShowMoreElement);
        }
        else{
            filterShowMoreElement = driver.findElement(By.xpath("//div[@data-testid='search-filter-group-"+filtername+"']"));
            Actionutil.scrollToElementAction(driver,filterShowMoreElement);
        }
    }


    public int getCount(String filtername){
        List<WebElement> listOfOptions = driver.findElements(By.xpath("(//div[@data-testid='search-filter-group-"+filtername+"']//span[@class='css-ebbjlp']/parent::span)"));
        System.out.println("List of " + filtername +" are: "+ listOfOptions.size());
    }

    public void getOptions(String filtername){
        List<WebElement> listOfOptions = driver.findElements(By.xpath("(//div[@data-testid='search-filter-group-"+filtername+"']//span[@class='css-ebbjlp']/parent::span)"));
        System.out.println("List of " + filtername +" are: ");
        for(int i=0;i<listOfOptions.size();i++){
            WebElement eachOption = listOfOptions.get(i);
            System.out.println(eachOption.getText());
        }
    }


}
