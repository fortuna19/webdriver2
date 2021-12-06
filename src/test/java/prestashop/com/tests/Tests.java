package prestashop.com.tests;

import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import prestashop.com.pages.*;
import prestashop.com.utils.ConfProperties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static prestashop.com.pages.FullProductItem.*;
import static prestashop.com.utils.Utils.*;

@Listeners({TestListener.class})
public class Tests extends BaseTest {

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
    @Description("Verify the user is able to register")
    @Epic("EPIC001")
    @Feature("Feature1: Registration")
    @Story("Story: Registration")
    @Step("Step: Verify user is able to register")
    @Severity(SeverityLevel.CRITICAL)
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
    @Description("Verify the user is able to login")
    @Epic("EPIC002")
    @Feature("Feature2: Login")
    @Story("Story: Login")
    @Step("Step: Verify user is able to login")
    @Severity(SeverityLevel.BLOCKER)
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
    @Description("Verify the user is able to filter products by color")
    @Epic("EPIC003")
    @Feature("Feature3: Filter")
    @Story("Story: Filter by color")
    @Step("Step: Verify user is able to filter products by color")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Verify the user is able to filter products by sizes")
    @Epic("EPIC003")
    @Feature("Feature3: Filter")
    @Story("Story: Filter by sizes")
    @Step("Step: Verify user is able to filter products by size")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Verify the user is able to sort products from A to Z")
    @Epic("EPIC003")
    @Feature("Feature3: Sorting")
    @Story("Story: Sort A to Z")
    @Step("Step: Verify user is able to sort products from A to Z")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Verify the user is able to add products to wishlist")
    @Epic("EPIC004")
    @Feature("Feature4: Wishlist")
    @Story("Story: Adding products to wishlist")
    @Step("Step: Verify user is able to add products to wishlist")
    @Severity(SeverityLevel.NORMAL)
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
