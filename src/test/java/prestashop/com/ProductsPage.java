package prestashop.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='nofollow']")
    private List<WebElement> paginationPages;

    @FindBy(css = "article h2[@itemprop='name']")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//article//span[@class='price']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//article//span[@class='Regular price']")
    private List<WebElement> productRegularPrices;

    @FindBy(xpath = "//article//span[@class='discount-percentage discount-product']")
    private List<WebElement> productDiscount;

    public List<ProductItem> getAllProducts() throws InterruptedException {
        List<ProductItem> products = new ArrayList<>();
        for (int i = 0; i < paginationPages.size(); i++){
            paginationPages.get(i).click();
            Thread.sleep(3000);
            for (int j = 0; j < productTitles.size(); j++){
                String title = productTitles.get(j).getText();
                String price = productPrices.get(j).getText();
                products.add(new ProductItem(title, price));
            }
        }
        return products;
    }
}
