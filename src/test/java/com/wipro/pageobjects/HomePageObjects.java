package com.wipro.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

public class HomePageObjects {

    @AndroidFindBy(accessibility = "Prime Prime")
    private MobileElement primeButton;

    @AndroidFindBy(accessibility = "Pantry Pantry")
    private MobileElement pantryCategoryButton;

    @AndroidFindBy(accessibility = "Mobiles Mobiles")
    private MobileElement mobilesCategoryButton;

    @AndroidFindBy(accessibility = "Fashion Fashion")
    private MobileElement fashionCategoryButton;

    @AndroidFindBy(accessibility = "Home Home")
    private MobileElement homeCategoryButton;

    @AndroidFindBy(accessibility = "Appliances Appliances")
    private MobileElement appliancesCategoryButton;

    @AndroidFindBy(accessibility = "Electronics Electronics")
    private MobileElement electronicsCategoryButton;

    @AndroidFindBy(xpath = "//*[@content-desc='Pantry Pantry']/../../..")
    private MobileElement categoryScrollBar;

    @AndroidFindBy(xpath = "//*[@text='New & upcoming launches in electronics']")
    private MobileElement electronicsPageHeader;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/error_message")
    private MobileElement noInternetError;

    public MobileElement getPrimeButton() {
        return primeButton;
    }

    public MobileElement getPantryCategoryButton() {
        return pantryCategoryButton;
    }

    public MobileElement getMobilesCategoryButton() {
        return mobilesCategoryButton;
    }

    public MobileElement getFashionCategoryButton() {
        return fashionCategoryButton;
    }

    public MobileElement getHomeCategoryButton() { return homeCategoryButton; }

    public MobileElement getAppliancesCategoryButton() {
        return appliancesCategoryButton;
    }

    public MobileElement getElectronicsCategoryButton() {
        return electronicsCategoryButton;
    }

    public MobileElement getCategoryScrollBar(){return categoryScrollBar;}

    public MobileElement getElectronicsPageHeader(){return electronicsPageHeader;}

    public MobileElement getNoInternetError(){return noInternetError;}


}
