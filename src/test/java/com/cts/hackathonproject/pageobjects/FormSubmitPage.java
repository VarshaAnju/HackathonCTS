package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.JavaScriptUtil;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormSubmitPage extends BasePage {
    private WebDriver driver;

    public FormSubmitPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "FirstName")
    private WebElement firstElement;

    @FindBy(id="LastName")
    private WebElement lastName;

    @FindBy(id="Email")
    private WebElement emailElement;

    @FindBy(id="Phone")
    private WebElement phoneElement;

    @FindBy(id="rentalField9")
    private WebElement orgElement;

    @FindBy(id="Company")
    private WebElement companyElement;

    @FindBy(id="Employee_Range__c")
    private WebElement compSizeElement;

    @FindBy(id="Country")
    private WebElement countryElement;

    @FindBy(xpath="//button[@type=\"submit\"]")
    private WebElement submitButton;



    @FindBy(xpath="//p[@class='p1']")
    private WebElement formPara;


    public void goToForm(){
        JavaScriptUtil.JSscrollToElement(firstElement,driver);
    }

    public String formProof(){
        return formPara.getText();
    }

    @FindBy(xpath="//div[@role='alert']")
    private WebElement errorMsgDisplayElement;

    public void setFirstElement(String name){
        firstElement.sendKeys(name);
    }

    public void setLastName(String name){
        lastName.sendKeys(name);
    }

    public void setEmailElement(String mail){
        emailElement.sendKeys(mail);
    }

    public void setPhoneElement(String num){
        phoneElement.sendKeys(num);
    }

    public void setOrgElement(String name){
        orgElement.sendKeys(name);
    }

    public void setCompanyElement(String name){
        companyElement.sendKeys(name);
    }

    public void setCompSizeElement(String size){
        compSizeElement.sendKeys(size);
    }

    public void setCountryElement(String name){
        countryElement.sendKeys(name);
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public String getErrorMessage(WebDriver driver){
        return errorMsgDisplayElement.getText();
    }

}
