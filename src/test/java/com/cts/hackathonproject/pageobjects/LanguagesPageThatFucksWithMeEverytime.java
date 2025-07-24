package com.cts.hackathonproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LanguagesPageThatFucksWithMeEverytime extends BasePage{

    private WebDriver driver;

    public LanguagesPageThatFucksWithMeEverytime(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[@class='_1q9sh65']")
    private List<WebElement> languagesList;

    public void getLanguagesList(){
        System.out.println("The Popular Languages offered are:");
        for(int i=0;i<languagesList.size();i++){
            WebElement eachElement = languagesList.get(i);
            System.out.println(eachElement.getText());
        }
    }

    public int getLanguagesCount(){
        return languagesList.size();
    }




}
