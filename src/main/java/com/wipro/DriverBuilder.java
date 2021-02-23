package com.wipro;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverBuilder extends Base{


    private static AppiumDriverLocalService appiumDriverLocalService = null;


    /**
     * Capabilities for android device
     * @return
     */
    private static DesiredCapabilities getAndroidMobileCapabilities(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/androidConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/"+properties.getProperty("APP_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,"true");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,properties.getProperty("DEVICE_VERSION"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,properties.getProperty("DEVICE_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.UDID,properties.getProperty("DEVICE_UDID"));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, properties.getProperty("APP_PACKAGE"));
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, properties.getProperty("APP_ACTIVITY"));
        return desiredCapabilities;
    }

    /**
     * Start appium server
     * @return
     */
    private AppiumDriverLocalService startAppiumServer(){
        int appiumPort = getPort();
        String appiumServerIP = "127.0.0.1";
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withAppiumJS(new File(System.getenv("APPIUM_HOME")))
                .withIPAddress(appiumServerIP)
                .usingPort(appiumPort)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"error")
                .withLogFile(new File(System.getProperty("user.dir") + "/target/appiumlogs/appium_server_logs.log"))
                ;
        logMessage("Starting appium driver server at "+appiumServerIP+":"+appiumPort);
        AppiumDriverLocalService appiumDriverLocalService= appiumServiceBuilder.build();
        appiumDriverLocalService.start();
        return appiumDriverLocalService;
    }

      AndroidDriver<MobileElement> getDriver(){
        try{
        AndroidDriver<MobileElement> androidDriver;
        logMessage("Creating android driver instance");
        appiumDriverLocalService = startAppiumServer();
        androidDriver =  new AndroidDriver<MobileElement>(appiumDriverLocalService.getUrl(),getAndroidMobileCapabilities());
        logMessage("Successfully created android driver instance");
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return androidDriver;
        }catch (Exception e){
            e.printStackTrace();
            logError(e.getMessage());
            return null;
        }
    }


    /**
     * get free available ports on machine dynamically
     * @return
     */
    private static int getPort()  {
        int port = 0;
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
