package prestashop.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static prestashop.com.utils.Utils.sleep;

public class ProductsListPage {
    WebDriver driver;

    public ProductsListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='nofollow']")
    private List<WebElement> paginationPages;

    @FindBy(xpath = "//article")
    private List<WebElement> productsOnPage;

    @FindBy(xpath = "//article//span[@class='discount-percentage discount-product']")
    private List<WebElement> productDiscount;

    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement productTitle;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement productPrice;

    @FindBy(xpath = "//span[@class='regular-price']")
    private WebElement productRegularPrice;

    @FindBy(xpath = "//select[@class='form-control form-control-select']/option")
    private List<WebElement> productSizes;

    @FindBy(xpath = "//div[@id='search_filters']")
    private WebElement leftMenuBlock;

    @FindBy(xpath = "//section[@class='facet clearfix']")
    private List<WebElement> searchFilters;

    @FindBy(xpath = "//section[@class='facet clearfix'][3]//li[2]")
    private WebElement filterByBlackColorCheckbox;

    @FindBy(xpath = "//section[@class='facet clearfix'][2]//li[1]")
    private WebElement filterBySizeS;

    @FindBy(xpath = "//button[@aria-label='Sort by selection']")
    private WebElement sortByDropdown;

    @FindBy(xpath = "(//div[@class='dropdown-menu']/a)[3]")
    private WebElement sortByNameAToZ;

    public void filterByBlackColor() {
        filterByBlackColorCheckbox.click();
    }

    public void filterBySizeS() {
        filterBySizeS.click();
    }

    public List<ProductItem> getAllProducts() {
        List<ProductItem> products = new ArrayList<>();
        int pages;
        sleep(1000);

        if (driver.findElements(By.xpath("//nav[@class='pagination']//a[@rel='nofollow']")).size() > 0) {
            pages = paginationPages.size();
        } else {
            pages = 1;
        }

        for (int i = 0; i < pages; i++) {
            if (driver.findElements(By.xpath("//nav[@class='pagination']//a[@rel='nofollow']")).size() > 0) {
                paginationPages.get(i).click();
            }

            sleep(1000);

            for (int j = 0; j < productsOnPage.size(); j++) {

                String title = productsOnPage.get(j).findElement(By.tagName("h2")).getText();
                double price = Double.parseDouble(productsOnPage.get(j).findElement(By.className("price")).getText().substring(1));
                String url = productsOnPage.get(j).findElement(By.tagName("a")).getAttribute("href");
                products.add(new ProductItem(title, price, url));
            }
        }
        return products;
    }

    public void clickSortByDropdown() {
        sortByDropdown.click();
        sleep(2000);
    }

    public void sortByNameAToZ() {
        clickSortByDropdown();
        sortByNameAToZ.click();
        sleep(2000);
    }

}

