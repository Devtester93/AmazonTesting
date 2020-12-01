package amazon.test.java.pageobject;

public class SettingMenuPageAndriod implements ShoppingMenuPage  {

    private AppiumDriver _driver;
    public ShoppingMenuPageAndroid(AppiumDriver driver) {
        _driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator ="new UiSelector().resourceId(\"nav-hamburger-menu\")")
    private MobileElement hamburgerMenu;

    //This is the step to scroll into the view area then find the value
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId([contains(@text,'Settings')])")
    private MobileElement settingsMenu;

    @AndroidFindBy(uiAutomator ="new UiSelector().resourceId.contains[text(),(\"Change country/region.\")]")
    private MobileElement changeCountryName;

    @AndroidFindBy(uiAutomator ="new UiSelector().resourceId(\"Continue\")")
    private MobileElement countinueBtn;

    @AndroidFindBy(uiAutomator ="new UiSelector().resourceId(\"country\")")
    private MobileElement getcountryName;

    public void clickOnCountry(String countryName) {
        searchEditText.click();
        searchEditText.sendKeys(productName);
        ((AndroidDriver) _driver).pressKeyCode(AndroidKeyCode.ENTER);

        //Clicking Hamburger Menu
        hamburgerMenu.click();
        settingsMenu.click();
        changeCountryName.click();

        List<MobileElement> country = _driver.findElements(By.xpath("//android.widget.TextView"));

        for (int i = 0; i < country.size(); i++) {
            if (country.get(i).getText().equalsIgnoreCase(countryName)) {

                country.get(i).click();

            }
        }
        countinueBtn.click();

        String countryNameDetail = getcountryName.getText();

        Assert.assertTrue("The product Country details matched",countryNameDetail.equalignorecase(countryName));
    }
}
