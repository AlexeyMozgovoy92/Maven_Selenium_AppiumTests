import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Calculator {

    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();


    public void initialize() {

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 6 Pro");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.miui.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"cal.CalculatorActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void checkOneNumber() {
        driver.findElementById("com.miui.calculator:id/btn_3_s").click();
        assert driver.findElementByAndroidUIAutomator("com.miui.calculator:id/btn_3_s").isDisplayed();
    }


    @Disabled
    @Test
    public void checkCalculate() {
        initialize();
        driver.findElementById("com.miui.calculator:id/btn_3_s").click();
        driver.findElementById("com.miui.calculator:id/btn_plus_s").click();
        driver.findElementById("com.miui.calculator:id/btn_9_s").click();
        driver.findElementById("com.miui.calculator:id/btn_equal_s").click();
        assert (driver.findElementByAndroidUIAutomator("new UiSelector().packageName(\"com.miui.calculator\").resourceId(\"com.miui.calculator:id/result\")").getText().equals("= 12"));
    }


}