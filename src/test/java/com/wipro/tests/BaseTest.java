package com.wipro.tests;

import com.wipro.Base;
import com.wipro.DriverBuilder;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.sql.DriverManager;

public class BaseTest extends Base {



    @BeforeClass
    public void initDevice(){
      initAndroidDriver();
    }

    @AfterClass
    public void killDriver(){
        androidDriver.quit();
    }

    @BeforeMethod
    public void restartApp(){
        relaunchApp();
    }



}
