package amazon.test.java;

import amazon.test.java.appiumSupport.AppiumBaseClass;
import amazon.test.java.appiumSupport.AppiumController;
import amazon.test.java.pageobject.*;
import org.junit.After;
import org.junit.Before;


public class SelectCountry extends BaseTestClass {

    @Test
    public void ClickCountry()  {
        SettingMenuPage.clickOnCountry("Australia (English)");
    }

}