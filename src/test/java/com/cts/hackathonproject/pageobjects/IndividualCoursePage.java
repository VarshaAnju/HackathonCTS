package com.cts.hackathonproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndividualCoursePage extends BasePage{

    private WebDriver driver;

    @FindBy(xpath="")
    WebElement titleElement;
    @FindBy(xpath="")
    WebElement ratingsElement;

    public IndividualCoursePage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void getTitleOfCourse(){

    }

}
