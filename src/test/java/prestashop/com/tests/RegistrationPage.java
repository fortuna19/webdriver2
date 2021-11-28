package prestashop.com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className= "register-form")
    private WebElement registrationForm;

    @FindBy(css = "div.col-md-6.form-control-valign label:first-child")
    private WebElement socialTitleMr;

    @FindBy(css = "div.col-md-6.form-control-valign label:last-child")
    private WebElement socialTitleMrs;

    @FindBy(id = "field-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "field-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "field-email")
    private WebElement emailInput;

    @FindBy(id = "field-password")
    private WebElement passwordInput;

    @FindBy(id = "field-birthday")
    private WebElement birthdateInput;

    @FindBy(css = "input[name='customer_privacy']")
    private WebElement customerDataPrivacyCheckbox;

    @FindBy(css = "input[name='psgdpr']")
    private WebElement agreeTermsCheckbox;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(className = "account")
    private WebElement userNameLastname;

    public void chooseSocialTitle(String socialTitle) {
        if (socialTitle.equals("Mr")) {
            socialTitleMr.click();
        } else {
            socialTitleMrs.click();
        }
    }

    public void clickFirstNameField() {
        firstNameInput.click();
    }

    public void clickLastNameInput() {
        lastNameInput.click();
    }

    public void clickEmailInput() {
        emailInput.click();
    }

    public void clickPasswordInput() {
        passwordInput.click();
    }

    public void clickBirthdateInput() {
        birthdateInput.click();
    }


    public void enterTextInField(String field, String text) {
        WebElement inputField = null;

        if (field.equals("First name")) {
            inputField = firstNameInput;
        } else if (field.equals("Last name")) {
            inputField = lastNameInput;
        } else if (field.equals("Email")) {
            inputField = emailInput;
        } else if (field.equals("Password")) {
            inputField = passwordInput;
        } else if (field.equals("Birthdate")) {
            inputField = birthdateInput;
        }

        inputField.sendKeys(text);
    }

    public void clickCustomerDataPrivacyCheckbox() {
        customerDataPrivacyCheckbox.click();
    }

    public void clickAgreeTermsCheckbox() {
        agreeTermsCheckbox.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public String getUserNameLastname() {
        return userNameLastname.getText();
    }
}
