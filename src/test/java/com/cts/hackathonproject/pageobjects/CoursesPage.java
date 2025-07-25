package com.cts.hackathonproject.pageobjects;

import com.cts.hackathonproject.utils.seleniumutils.Actionutil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CoursesPage extends BasePage {

    private WebDriver driver;

    public CoursesPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h3[@class=\"cds-CommonCard-title css-6ecy9b\"]")
    private List<WebElement> listOfCourseCards;

    @FindBy(xpath="//h3[@class=\"cds-CommonCard-title css-6ecy9b\"]")
    private List<WebElement> nameOfCourseCard;

    public int getCountOfCourses(){
        return listOfCourseCards.size();
    }

    public void findFilter(String filter){

        WebElement filterShowMoreElement;
        if(filter.equals("Language")||filter.equals("Subject")||filter.equals("Subtitles")||filter.equals("Skills")){
            filterShowMoreElement = driver.findElement(By.xpath("//div[@data-testid='search-filter-group-"+filter+"']//span[@class='cds-button-label']"));
            Actionutil.scrollToElementAction(driver,filterShowMoreElement);
            Actionutil.clickOnElement(driver,filterShowMoreElement);
        }
        else{
            filterShowMoreElement = driver.findElement(By.xpath("//div[@data-testid='search-filter-group-"+filter+"']"));
            Actionutil.scrollToElementAction(driver,filterShowMoreElement);
        }

    }

    public void clickOnFilter(String filter, String nameOfFilter){

        List<WebElement> listOfOptions = driver.findElements(By.xpath("(//div[@data-testid='search-filter-group-"+filter+"']//span[@class='css-ebbjlp']/parent::span)"));
        for(int i=0;i<listOfOptions.size();i++){
            WebElement eachOption = listOfOptions.get(i);
            String he = eachOption.getText();
            String cleaned = he.replaceAll("\\(.*?\\)", "").trim();
            //System.out.println(cleaned);
            if(cleaned.equalsIgnoreCase(nameOfFilter)){
                eachOption.click();
                return;
            }
        }
    }

    public void clickOnCourseCard(int courseIndex){
        driver.findElement(By.xpath("(//div[@class='cds-ProductCard-header'])["+courseIndex+"]")).click();
    }

    public void shiftWindow(int index){
        Set<String> allwin = driver.getWindowHandles();
        List<String> all = new ArrayList<>(allwin);
        driver.switchTo().window(all.get(index));
    }

    @FindBy(xpath="//span[@class=\"cds-Chip-label\"]")
    private List<WebElement> listOfFiltersApplied;

    public int countOfFiltersApplied(){
        return listOfFiltersApplied.size();
    }

    public String getCourseCardName(int index){
        return nameOfCourseCard.get(index).getText();
    }




}
