package prestashop.com.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import prestashop.com.pages.*;
import prestashop.com.utils.ConfProperties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static prestashop.com.utils.Utils.deserializeObject;
import static prestashop.com.utils.Utils.sleep;

public class BaseTest {
    public WebDriver driver;
    WebDriverWait wait;

    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static ProductsListPage productsListPage;
    public static ProductPage productPage;
    public static RegistrationPage registrationPage;
    public static List<FullProductItem> fullProductItemList;

    public WebDriver getDriver() {
        return driver;
    }

    Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    void setupClass() throws IOException, ClassNotFoundException {
        WebDriverManager.chromedriver().setup();

        ObjectInputStream objectInputStream = deserializeObject("src/test/resources/product_items.file");
        fullProductItemList = (List<FullProductItem>) objectInputStream.readObject();
    }

    @BeforeMethod
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);
        driver.get(ConfProperties.getProperty("homepage"));
        sleep(5000);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        registrationPage = new RegistrationPage(driver);

    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }
}
