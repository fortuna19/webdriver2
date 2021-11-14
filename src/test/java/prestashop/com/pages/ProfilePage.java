package prestashop.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[text()='Oleksandr Rozhok']")
    private WebElement userName;

    @FindBy(xpath = "//a[@id='identity-link']")
    private WebElement informationMenu;

    @FindBy(xpath = "//a[@id='address-link']")
    private WebElement addFirstAddressMenu;

    @FindBy(xpath = "//a[@id='history-link']")
    private WebElement historyMenu;

    @FindBy(xpath = "//a[@id='order-slips-link']")
    private WebElement creditSlipsMenu;

    @FindBy(xpath = "//a[@id='psgdpr-link']")
    private WebElement gdprPersonalDataMenu;

    @FindBy(xpath = "//footer[@class='page-footer']//a")
    private WebElement signOutLink;

    public String getUserName() {
        return userName.getText();
    }

    public void clickInformationMenu() {
        informationMenu.click();
    }

    public void clickAddFirstAddressMenu() {
        addFirstAddressMenu.click();
    }

    public void clickOrderHistoryMenu() {
        historyMenu.click();
    }

    public void clickCreditSlipsMenu() {
        creditSlipsMenu.click();
    }

    public void clickGDPRPersonalDataMenu() {
        gdprPersonalDataMenu.click();
    }

    public void clickSignOut() {
        signOutLink.click();
    }
}
