package com.wipro.pages;

import com.wipro.Base;
import com.wipro.pageobjects.HomePageObjects;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base {

    private HomePage(){}

    HomePageObjects homePageObjects = new HomePageObjects();
    public HomePage(AndroidDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),homePageObjects);
    }

    public void clickPrimeButton(){
        homePageObjects.getPrimeButton().click();
    }

    public void clickPantryButton(){homePageObjects.getPantryCategoryButton().click();}

    public void clickMobilesCategoryButton(){homePageObjects.getMobilesCategoryButton().click();}

    public void clickFashionCategoryButton(){homePageObjects.getFashionCategoryButton().click();}

    public void clickHomeCategoryButton(){homePageObjects.getHomeCategoryButton().click();}

    public void clickAppliancesCategoryButton(){
    if(!isElementVisible(homePageObjects.getAppliancesCategoryButton())){
        swipeLeftOnCategoryScrollBar();
    }
        homePageObjects.getAppliancesCategoryButton().click();
    }

    public void clickElectronicsCategoryButton(){
        if(!isElementVisible(homePageObjects.getElectronicsCategoryButton())){
            swipeLeftOnCategoryScrollBar();
        }
        homePageObjects.getElectronicsCategoryButton().click();
    }

    public void swipeLeftOnCategoryScrollBar(){
        swipeOnElement(homePageObjects.getCategoryScrollBar(),"left");
    }

    public void swipeRightOnCategoryScrollBar(){
        swipeOnElement(homePageObjects.getCategoryScrollBar(),"right");
    }

    public Boolean isElectronicsPageHeaderDisplayed(){
        return isElementVisible(homePageObjects.getElectronicsPageHeader());
    }


}
