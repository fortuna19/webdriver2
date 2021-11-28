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

}
