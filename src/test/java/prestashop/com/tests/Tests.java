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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static prestashop.com.pages.FullProductItem.*;
import static prestashop.com.utils.Utils.*;

public class Tests {

    WebDriver driver;
    WebDriverWait wait;
    public static MainPage mainPage;
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static ProductsListPage productsListPage;
    public static ProductPage productPage;
    public static RegistrationPage registrationPage;
    public static List<FullProductItem> fullProductItemList;

    @BeforeTest
    static void setupClass() throws IOException, ClassNotFoundException {
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

    @Test(enabled = false) //this test is skipped by default
    public void serializeAllProducts(){
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        List<ProductItem> shortProducts = new ArrayList<>();
        shortProducts = productsListPage.getAllProducts();

        List<FullProductItem> fullProducts = new ArrayList<>();
        fullProducts = productPage.getFullProductItemslist(shortProducts);

        try {
            serializeObject(fullProducts, "src/test/resources/product_items.file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (FullProductItem fullProduct : fullProducts) {
            System.out.println(fullProduct.toString());
        }
    }

    @Test(priority = 1)
    public void registration() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the Sign In button");
        mainPage.clickSignInButton();

        logger.info("Click the 'No account? Create one here' link");
        loginPage.clickCreateNewAccountLink();

        logger.info("Choose the social title");
        registrationPage.chooseSocialTitle("Mr");

        logger.info("Enter first name");
        registrationPage.clickFirstNameField();
        registrationPage.enterTextInField("First name", ConfProperties.getProperty("username"));

        logger.info("Enter last name");
        registrationPage.clickLastNameInput();
        registrationPage.enterTextInField("Last name", ConfProperties.getProperty("lastname"));

        logger.info("Enter email");
        registrationPage.clickEmailInput();
        registrationPage.enterTextInField("Email", ConfProperties.getProperty("email"));

        logger.info("Enter password");
        registrationPage.clickPasswordInput();
        registrationPage.enterTextInField("Password", ConfProperties.getProperty("password"));

        logger.info("Enter birthdate");
        registrationPage.clickBirthdateInput();
        registrationPage.enterTextInField("Birthdate", ConfProperties.getProperty("birthdate"));

        logger.info("Click all necessary checkboxes");
        registrationPage.clickCustomerDataPrivacyCheckbox();
        registrationPage.clickAgreeTermsCheckbox();

        logger.info("Click the Save button");
        registrationPage.clickSaveButton();

        logger.info("Verify user is logged in and see his name");
        Assert.assertEquals(registrationPage.getUserNameLastname(), (ConfProperties.getProperty("username") + " " + ConfProperties.getProperty("lastname")));
    }

    @Test(priority = 2)
    public void loginTest() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the Sign In button");
        mainPage.clickSignInButton();

        logger.info("Enter user email and click the Tab key");
        loginPage.inputEmail(ConfProperties.getProperty("email"));

        logger.info("Enter user password");
        loginPage.inputPassword(ConfProperties.getProperty("password"));

        logger.info("Click the Sign in button");
        loginPage.clickSignInButton();

        logger.info("Verify user is logged in and see his name");
        Assert.assertEquals(registrationPage.getUserNameLastname(), (ConfProperties.getProperty("username") + " " + ConfProperties.getProperty("lastname")));
    }

    @Test
    public void checkFilterByColor() {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        logger.info("Filter all products by the Black color and retrieve their titles");
        productsListPage.filterByBlackColor();
        List<String> productsOnPage = productsListPage.getAllProducts().stream().map(e -> e.getTitle().toLowerCase(Locale.ROOT)).collect(Collectors.toList());

        logger.info("Verify all products filtered as expected");
        List<String> filteredByColor = filterByColor(fullProductItemList, "Black");
        Assert.assertEquals(productsOnPage, filteredByColor);
    }

    @Test
    public void checkFilterBySizeS() {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        logger.info("Filter all products by the S Size and retrieve their titles");
        productsListPage.filterBySizeS();
        List<String> productsOnPage = productsListPage.getAllProducts().stream().map(e -> e.getTitle().toLowerCase(Locale.ROOT)).collect(Collectors.toList());

        logger.info("Verify all products filtered as expected");
        List<String> filteredBySizes = filterBySize(fullProductItemList, "S");
        Assert.assertEquals(productsOnPage, filteredBySizes);
    }

    @Test
    public void sortProducts(){
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Click the All products link");
        mainPage.clickAllProductsLink();

        logger.info("Sort all products by Name from A to Z");
        productsListPage.sortByNameAToZ();
        List<String> productsOnPages = productsListPage.getAllProducts().stream().map(e -> e.getTitle().toLowerCase(Locale.ROOT)).collect(Collectors.toList());
        cutLongTitles(productsOnPages);

        logger.info("Verify all products are sorted as expected");
        List<String> sortedProducts = sortByNameAToZ(fullProductItemList);
        cutLongTitles(sortedProducts);
        Assert.assertEquals(productsOnPages, sortedProducts);
    }

    @Test(priority = 3)
    public void addProductToWishlist(){
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("Open the main page and click the Hide button");
        mainPage.clickHideButton();

        logger.info("Switch from top frame to main content");
        mainPage.switchToMainContent();

        logger.info("Log in");
        mainPage.clickSignInButton();
        loginPage.inputEmail(ConfProperties.getProperty("email"));
        loginPage.inputPassword(ConfProperties.getProperty("password"));
        loginPage.clickSignInButton();

        logger.info("Click My Store button");
        mainPage.clickMyStoreButton();

        logger.info("Click Add to wishlist icon for the first product on the page");
        mainPage.clickAddToWishListFirstProduct();

        logger.info("Choose default wishlist");
        mainPage.clickDefaultWishlist();

        logger.info("Click on the user name");
        profilePage.clickUserName();

        logger.info("Click on My Wishlist menu");
        profilePage.clickMyWishlistMenu();

        logger.info("Choose default wishlist");
        mainPage.clickDefaultWishlist();

        /**** continue work on this test ***/
    }

    public static List<FullProductItem> parseAllProducts(List<ProductItem> products) throws InterruptedException {
        List<FullProductItem> fullProductItemList = productPage.getFullProductItemslist(products);
        for (int i = 0; i < fullProductItemList.size(); i++) {
            System.out.println(fullProductItemList.get(i).toString());
        }
        return fullProductItemList;
    }
}
