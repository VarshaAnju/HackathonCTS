package com.cts.hackathonproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndividualCoursePage extends BasePage{

    private WebDriver driver;

    @FindBy(xpath="//h1[@class=\"cds-119 css-1rmg2ag cds-121\"]")
    WebElement titleElement;

    public IndividualCoursePage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public String getTitleOfCourse(){
        return titleElement.getText();
    }

    @FindBy(xpath="(//div[@class=\"css-fk6qfz\"])[3]")
    private WebElement ratingsElement;

    @FindBy(xpath="(//div[@class=\"css-6mrk5o\"])[4]")
    private WebElement timeElement;

    @FindBy(xpath="//h1[@class='cds-119 css-1rmg2ag cds-121']")
    private WebElement nameElement;

    public String getRatings(){
        return ratingsElement.getText();
    }

    public String getNameOfCourse(){
        String name = nameElement.getText();
        return name;
    }

    public String getDuration(){
        return timeElement.getText();
    }

}
