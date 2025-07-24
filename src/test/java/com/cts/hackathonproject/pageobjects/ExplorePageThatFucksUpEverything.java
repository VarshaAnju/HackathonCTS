package com.cts.hackathonproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v135.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExplorePageThatFucksUpEverything extends BasePage{

    private WebDriver driver;

    public ExplorePageThatFucksUpEverything(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@data-testid=\"TopicAndSkillsWrapper\"]//span[@class=\"domain-card-name\"]")
    private List<WebElement> categoriesList;

    @FindBy(xpath="(//button[@aria-label='Slide 2'])[1]")
    private WebElement slideButton;

    public int getCountOfCategories(){
        return categoriesList.size();
    }

    public void clickOnCategory(String categoryName){

        for(int i=0;i<5;i++){
            WebElement eachCategory = categoriesList.get(i);
            if(eachCategory.getText().equalsIgnoreCase(categoryName)){
                eachCategory.click(); return;
            }
        }

        slideButton.click();

        for(int i=5;i<categoriesList.size();i++){
            WebElement eachCategory = categoriesList.get(i);
            if(eachCategory.getText().equalsIgnoreCase(categoryName)){
                eachCategory.click(); return;
            }
        }
    }




}
