package prestashop.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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




    /*Working method*/
    public List<ProductItem> getAllProducts() throws InterruptedException {
        List<ProductItem> products = new ArrayList<>();
        List<String> sizes = new ArrayList<>();
        for (int i = 0; i < paginationPages.size(); i++) {
            paginationPages.get(i).click();
            Thread.sleep(1000);
            for (int j = 0; j < productsOnPage.size(); j++) {

                String title = productsOnPage.get(j).findElement(By.xpath(String.format("(//h2)[%d]", j + 1))).getText();
                double price = Double.parseDouble(productsOnPage.get(j).findElement(By.xpath(String.format("(//span[@class='price'])[%d]", j + 1))).getText().substring(1));
                String url = productsOnPage.get(j).findElement(By.xpath(String.format("(//div[@class='thumbnail-container reviews-loaded']/a)[%d]", j + 1))).getAttribute("href");
                products.add(new ProductItem(title, price, url));
            }
        }
        return products;
    }


}

