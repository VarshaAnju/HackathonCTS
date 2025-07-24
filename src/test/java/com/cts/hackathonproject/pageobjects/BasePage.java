package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.Actionutil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    private WebDriver driver;
    @FindBy(id="search-autocomplete-input")
    private WebElement searchBoxElement;


    public BasePage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(this.driver, this);
    }

    public void giveSearchKeyword(String name) {
        searchBoxElement.sendKeys(name);
    }

    public void clickSearchButton() {
        Actionutil.clickEnter(driver);
    }


}
