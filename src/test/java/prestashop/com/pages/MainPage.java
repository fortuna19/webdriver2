package prestashop.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "footer")
    private WebElement footer;

    @FindBy(css = ".user-info a")
    private WebElement signInButton;

    @FindBy(className = "hide-header")
    private WebElement hideButton;

    @FindBy(className = "all-product-link")
    private WebElement allProductsLink;

    @FindBy(css = "div.products.row .product:first-child .wishlist-button-add")
    private  WebElement addToWishlistFirstProduct;

    @FindBy(css = ".wishlist-list li:first-child p")
    private WebElement defaultWishlist;

    @FindBy(css = "#_desktop_logo")
    private WebElement myStoreButton;

    public void clickHideButton() {
        hideButton.click();
    }

    public void switchToMainContent() {
        driver.switchTo().frame(0);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void clickAllProductsLink(){
        allProductsLink.click();
    }

    public void clickAddToWishListFirstProduct(){
        addToWishlistFirstProduct.click();
    }

    public void clickDefaultWishlist(){
        defaultWishlist.click();
    }

    public void clickMyStoreButton(){
        myStoreButton.click();
    }
}
