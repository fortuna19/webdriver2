package prestashop.com;

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

    @FindBy(xpath = "//footer[@id='footer']")
    private WebElement footer;

    @FindBy(xpath = "//span[text()='Sign in']")
    private WebElement signInButton;

    @FindBy(xpath = "//span[text()='Hide']")
    private WebElement hideButton;

    @FindBy(xpath = "//section[@id='content']/section/a")
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
