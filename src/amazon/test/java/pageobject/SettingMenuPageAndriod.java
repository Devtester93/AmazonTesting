package amazon.test.java.pageobject;

public class SettingMenuPageAndriod implements ShoppingMenuPage  {

    private AppiumDriver _driver;
    public ShoppingMenuPageAndroid(AppiumDriver driver) {
        _driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id ="in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon")
    private MobileElement hamburgerMenu;

    @AndroidFindBy(name = "Settings")
    private MobileElement settingsMenu;

    @AndroidFindBy(name ="Change Country")
    private MobileElement changeCountryName;

    @AndroidFindBy(name ="Continue")
    private MobileElement countinueBtn;

    public void clickOnCountry(String countryName) {
        searchEditText.click();
        searchEditText.sendKeys(productName);
        ((AndroidDriver) _driver).pressKeyCode(AndroidKeyCode.ENTER);

        //Clicking Hamburger Menu
        hamburgerMenu.click();

        //Scroll and Touch to Settings Menu
        TouchActions action = new TouchActions(driver);
        action.scroll(settingsMenu, 10, 100);
        action.perform();

        changeCountryName.click();

        List<MobileElement> country = _driver.findElements(By.xpath("//android.widget.TextView"));

        for (int i = 0; i < country.size(); i++) {
            if (country.get(i).getText().equalsIgnoreCase(countryName)) {
                //Scroll and Touch to Settings Menu
                TouchActions action = new TouchActions(driver);
                action.scroll(country.get(i), 10, 100);
                action.perform();
                country.get(i).click();
            }
        }
        countinueBtn.click();

        String countryNameDetail = getcountryName.getText();
        Assert.assertTrue("The product Country details matched",countryNameDetail.equalignorecase(countryName));
        File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("C:/temp/Screenshot3.jpg"));
    }
}
