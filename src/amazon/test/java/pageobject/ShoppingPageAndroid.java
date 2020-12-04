package amazon.test.java.pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ShoppingPageAndroid implements ShoppingPage {

    private AppiumDriver _driver;
    public ShoppingPageAndroid(AppiumDriver driver) {
        _driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
    private MobileElement searchEditText;

    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/title")
    private MobileElement titleTextView;
    
    @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/priceblock_ourprice")
    private MobileElement priceTextView;

    public void validateProductInfo(String productName) {
        searchEditText.click();
        searchEditText.sendKeys(productName);
        ((AndroidDriver) _driver).pressKeyCode(AndroidKeyCode.ENTER);

        //Find any random product by regular expression
        String randomTVUiSelector="new UiSelector().textContains(\"65\").textContains(\"TV\").textContains(\"$\")";

        WebElement randomTvProduct= ((AndroidDriver) _driver).findElementByAndroidUIAutomator(randomTVUiSelector);

        String productDescriptionListing = randomTvProduct.getText();

        randomTvProduct.click();

        String  productDescriptionCheckOut = titleTextView.getText();

        TouchActions action = new TouchActions(driver);
        action.scroll(priceTextView, 10, 100);
        action.perform();

        String  productPriceCheckOut = priceTextView.getText();

        Assert.assertTrue("The product description text matched",productDescriptionCheckOut.contains(productDescriptionListing.substring(0, productDescriptionListing.indexOf("..."))));
        Assert.assertTrue("The price matched",productDescriptionListing.contains(productPriceCheckOut));
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:/temp/Screenshot2.jpg"));

    }
}
