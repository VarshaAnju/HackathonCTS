package com.cts.hackathonproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ExplorePage extends BasePage{

    private WebDriver driver;

    public ExplorePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath="(//span[@class=\"css-drx6ax\"])")
    private List<WebElement> listOfExploreCategoriesOptions;

    public void selectCategoryOption(String categoryName){
        for(int i=0;i<listOfExploreCategoriesOptions.size();i++){
            WebElement eachCategory = listOfExploreCategoriesOptions.get(i);
            if(eachCategory.getText().equalsIgnoreCase(categoryName)){
                eachCategory.click(); return;
            }
        }
    }

    public boolean isElementPresent(){
        try{
            driver.findElement(By.xpath("//h1[@class=\"css-1d9oias\"]")); return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
}
