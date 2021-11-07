package prestashop.com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@class='form-control']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@class='form-control js-child-focus js-visible-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id='submit-login']")
    private WebElement signInButton;

    public void inputEmail(String email) {
        emailInput.sendKeys(email, Keys.TAB);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }
}
