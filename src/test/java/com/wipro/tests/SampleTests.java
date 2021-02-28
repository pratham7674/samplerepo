package com.wipro.tests;

import com.google.common.collect.ImmutableMap;
import com.wipro.DriverBuilder;
import com.wipro.pages.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SampleTests  extends BaseTest {

    @Test(priority = 0,description = "Passing test")
    public void HomePageTestPass(){
        HomePage homePage = new HomePage(androidDriver);
        homePage.clickAppliancesCategoryButton();
        homePage.navigateBack();
        homePage.clickElectronicsCategoryButton();
        Assert.assertTrue(homePage.isElectronicsPageHeaderDisplayed(),"Electronics Page is not displayed after scrolling");
    }

    @Test(priority = 1,description = "Failing test")
    public void HomePageTestFail(){
        HomePage homePage = new HomePage(androidDriver);
        homePage.clickAppliancesCategoryButton();
        homePage.navigateBack();
        homePage.clickElectronicsCategoryButton();
        Assert.assertFalse(homePage.isElectronicsPageHeaderDisplayed(),"Electronics Page is not displayed after scrolling");
    }

    @Test(priority = 2, description = "Testing internet off scenario")
    public void noInternetTest(){
        HomePage homePage = new HomePage(androidDriver);
        WifiOff();
        relaunchApp();
        Assert.assertTrue(homePage.isNoInternetMessagePresent(),"No internet message is not displayed");
        WifiOn();
    }
}
