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

    @FindBy(xpath = "//div[@id='customer-form']")
    private WebElement registrationForm;

    @FindBy(xpath = "(//input[@name='id_gender'])[1]")
    private WebElement socialTitleMr;

    @FindBy(xpath = "(//input[@name='id_gender'])[2]")
    private WebElement socialTitleMrs;

    @FindBy(xpath = "//input[@id='field-firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='field-lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='field-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='field-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='field-birthday']")
    private WebElement birthdateInput;

    @FindBy(xpath = "//input[@name='customer_privacy']")
    private WebElement customerDataPrivacyCheckbox;

    @FindBy(xpath = "//input[@name='psgdpr']")
    private WebElement agreeTermsCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//a[@title='View my customer account']/span")
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
