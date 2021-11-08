package prestashop.com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import prestashop.com.pages.*;
import prestashop.com.utils.ConfProperties;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static prestashop.com.pages.FullProductItem.filterByColor;
import static prestashop.com.pages.FullProductItem.filterBySize;
import static prestashop.com.utils.Utils.*;

public class Tests {

    WebDriver driver;
    WebDriverWait wait;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static ProductsListPage productsListPage;
    public static ProductPage productPage;
    public static List<FullProductItem> fullProductItemList;

    @BeforeTest
    static void setupClass() throws IOException, ClassNotFoundException {
        WebDriverManager.chromedriver().setup();

        ObjectInputStream objectInputStream = deserializeObject("src/test/resources/product_items.file");
        fullProductItemList = (List<FullProductItem>) objectInputStream.readObject();
    }

    @BeforeMethod
    void setupTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);

        driver.get(ConfProperties.getProperty("homepage"));
        Thread.sleep(5000);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);

    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }

    @Test(enabled = false)
    public void loginTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the Sign In button");
        mainPage.clickSignInButton();

        logger.info("Enter user email and click the Tab key");
        loginPage.inputEmail(ConfProperties.getProperty("login"));

        logger.info("Enter user password");
        loginPage.inputPassword(ConfProperties.getProperty("password"));

        logger.info("Click the Sign in button");
        loginPage.clickSignInButton();

        logger.info("Verify user is logged in and see his name");
        Assert.assertEquals(profilePage.getUserName(), ConfProperties.getProperty("username"));
    }

    @Test
    public void checkFilterByColor() throws InterruptedException {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        logger.info("Filter all products by the Black color and retrieve their titles");
        productsListPage.filterByBlackColor();
        List<String> productsOnPage = productsListPage.getProductTitlesOnPage();

        logger.info("Verify all products filtered as expected");
        List<String> filteredByColor = filterByColor(fullProductItemList, "Black");
        Assert.assertEquals(productsOnPage, filteredByColor);
    }

    @Test
    public void checkFilterBySizeS() throws InterruptedException {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        logger.info("Filter all products by the S Size and retrieve their titles");
        productsListPage.filterBySizeS();
        List<String> productsOnPage = productsListPage.getProductTitlesOnPage();

        logger.info("Verify all products filtered as expected");
        List<String> filteredBySizes = filterBySize(fullProductItemList, "S");
        Assert.assertEquals(productsOnPage, filteredBySizes);
    }

    public static List<FullProductItem> parseAllProducts(List<ProductItem> products) throws InterruptedException {
        List<FullProductItem> fullProductItemList = productPage.getFullProductItemslist(products);
        for (int i = 0; i < fullProductItemList.size(); i++) {
            System.out.println(fullProductItemList.get(i).toString());
        }
        return fullProductItemList;
    }
}