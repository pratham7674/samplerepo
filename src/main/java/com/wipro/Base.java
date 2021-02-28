package com.wipro;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.HashMap;

/**
 * Base class for everything
 */

public class Base  {

    public static AndroidDriver<MobileElement> androidDriver;

    static {
        //for log generation purpose
        System.setProperty("log", System.getProperty("user.dir") + "/logs/ExecutionLog.log");
    }


    /*
    Start android driver
     */
    public void initAndroidDriver(){
        androidDriver = new DriverBuilder().getDriver();
    }

    /*

     */
    public void relaunchApp(){
        androidDriver.closeApp();
        androidDriver.launchApp();
    }

    /**
     * Log Info message to file
     * @param message message String
     */
    public void logMessage(String message){
       Logger log = Logger.getLogger("Execution");
       log.info(message);
    }

    public void logError(String message){
        Logger log = Logger.getLogger("Execution");
        log.error(message);
    }

    /**
     * Fluent wait init block
     * @return
     */
    private FluentWait getFluentWait(){
        return new FluentWait(androidDriver).
                withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(20));
    }

    /**
     * check for mobile element visibility
     * @param element Element to be tested
     * @return if element is visible return true else return false
     */
    public Boolean isElementVisible(MobileElement element){
        try {
            FluentWait wait = getFluentWait().ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception  e) {
            return false;
        }
    }


    /**
     * Universal method to scroll over element
     * @param element actionable element
     * @param direction scroll direction
     */
    public void swipeOnElement(MobileElement element, String direction){
        HashMap<String,Object> gesture = new HashMap<>();
        gesture.put("direction",direction);
        gesture.put("elementId",((RemoteWebElement) element).getId());
        gesture.put("percent",0.8);
        ((JavascriptExecutor) androidDriver) .executeScript("mobile: swipeGesture", gesture);
    }

    /**
     * press back key of android device
     */
    public void navigateBack(){
        androidDriver.navigate().back();
    }

    public void WifiOn() {
        ConnectionState state = androidDriver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        logMessage("WiFi turned on");
    }
    public void WifiOff() {
        ConnectionState state = androidDriver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        logMessage("WiFi turned off");
    }


}
