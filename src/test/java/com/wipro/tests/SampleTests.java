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

    @Test
    public void HomePageTest(){
        HomePage homePage = new HomePage(androidDriver);
        homePage.clickAppliancesCategoryButton();
        homePage.navigateBack();
        homePage.clickElectronicsCategoryButton();
        Assert.assertTrue(homePage.isElectronicsPageHeaderDisplayed(),"Electronics Page is not displayed after scrolling");
    }
}
