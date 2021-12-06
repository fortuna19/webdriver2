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

    @FindBy(css = ".account span")
    private WebElement userName;

    @FindBy(id = "identity-link")
    private WebElement informationMenu;

    @FindBy(id = "address-link")
    private WebElement addFirstAddressMenu;

    @FindBy(id = "history-link")
    private WebElement historyMenu;

    @FindBy(id = "order-slips-link")
    private WebElement creditSlipsMenu;

    @FindBy(id = "wishlist-link")
    private WebElement myWishlistMenu;

    @FindBy(id = "psgdpr-link")
    private WebElement gdprPersonalDataMenu;

    @FindBy(css = ".page-footer a")
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

    public void clickUserName(){
        userName.click();
    }

    public void clickMyWishlistMenu(){
        myWishlistMenu.click();
    }
}
