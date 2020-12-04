package amazon.test.java.pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

public class SignInPageAndroid implements SignInPage {

    public SignInPageAndroid(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@resourceId='in.amazon.mShop.android.shopping:id/sign_in_button']")
    private MobileElement alreadyACustomerSignButton;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/login_accordion_header")
    private MobileElement alreadyACustomerSignRadioButton;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/ap_email_login")
    private MobileElement emailUserNameEditText;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/continue")
    private MobileElement continueButton;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/ap_password")
    private MobileElement passwordEditText;

    @AndroidFindBy(id = "in.amazon.mShop.android.shopping:id/signInSubmit")
    private MobileElement signInButton;

    @AndroidFindBy(name = "Email a one-time passcode to")
    private MobileElement emailOTPRadioButton;

    @AndroidFindBy(className = "android.widget.EditText")
    private MobileElement emailOTPEditText;


    public void signIn(String amazonUserName,String amazonPassword) {

        try {
            alreadyACustomerSignButton.click();
            alreadyACustomerSignRadioButton.click();
            emailUserNameEditText.sendKeys(amazonUserName);
            continueButton.click();
            passwordEditText.sendKeys(amazonPassword);
            signInButton.click();
            //This can be automated through Push
            //For the given apk push notification is not being received
            //Hence considered email with manual intervention
            emailOTPRadioButton.click();
            continueButton.click();

            String otp = "1234";

            passwordEditText.sendKeys(otp);
            continueButton.click();
        }catch (NoSuchElementException ex){
            System.out.println("User is already logged in");
            File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("C:/temp/Screenshot1.jpg"));

        }

    }
}
