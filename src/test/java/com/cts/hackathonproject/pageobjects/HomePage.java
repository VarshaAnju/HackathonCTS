package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.Actionutil;
import com.cts.hackathonproject.utils.seleniumutils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class HomePage extends BasePage{

    protected WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

/*    @FindBy(xpath="(//div[@class=\"cds-9 css-0 cds-11 cds-grid-item cds-13 cds-52\"])[1]")
      private WebElement element;*/



    @FindAll({
            @FindBy(xpath = "//button[@class='cds-149 cds-button-disableElevation cds-button-ghost css-9ncsyc']/span[@class='cds-button-label']"),
            @FindBy(xpath="//span[@class='css-b8z8ax']")
    }
)
    private WebElement exploreElement;

    @FindBy(xpath="(//p[@class=\"css-yru9cv\"])")
    private List<WebElement> exploreCategoriesList;


    @FindBy(xpath="(//span[@class=\"css-drx6ax\"])")
    private List<WebElement> listOfExploreCategoriesOptions;

    public void clickExploreButton(){
        exploreElement.click();
    }

    public void selectExploreOption(String nameOfExploreOption){
        for(int i=0;i<exploreCategoriesList.size();i++){
            WebElement eachExploreOption = exploreCategoriesList.get(i);
            if(eachExploreOption.getText().equalsIgnoreCase(nameOfExploreOption)){
                eachExploreOption.click(); return;
            }
        }
    }

    public int getCategoriesCount(){
        int count = listOfExploreCategoriesOptions.size();
        return count;
    }

    @FindBy(xpath="//li[@class='rc-SubFooterSection__content-column-link-item lohp-rebrand']")
    private List<WebElement> listOfCatalogElements;

    /*public void clickOnDesiredCatalogElement(String elementName){
        JavaScriptUtil.JSscrollToElement(listOfCatalogElements.get(70),driver);
        for(int i=0;i<listOfCatalogElements.size();i++){
            WebElement eachElement = listOfCatalogElements.get(i);
            if(eachElement.getText().equalsIgnoreCase(elementName)){
                Actionutil.clickOnElement(driver,eachElement);
                //eachElement.click();
                return;
            }
        }
    }*/


    public void clickOnDesiredCatalogElement(String elementName){
        driver.findElement(By.linkText(elementName)).click();
    }

    public boolean isCatalogElementPresent(String elementName){
        boolean flag = false;
        JavaScriptUtil.JSscrollToElement(listOfCatalogElements.get(0),driver);
        for(int i=0;i<listOfCatalogElements.size();i++){
            WebElement eachElement = listOfCatalogElements.get(i);
            if(eachElement.getText().equalsIgnoreCase(elementName)){
                return true;
            }
        }
        return false;
    }


}
