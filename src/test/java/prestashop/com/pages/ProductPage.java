package prestashop.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static prestashop.com.utils.Utils.sleep;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(className = "current-price-value")
    private WebElement price;

    @FindBy(css = "div.product-description[id] p")
    private WebElement shortDescription;

    @FindBy(css = "div.product-variants span.control-label")
    private List<WebElement> productVariantsTitle;

    @FindBy(css = "#description p")
    private WebElement fullDescription;

    @FindBy(css = ".product-reference span")
    private WebElement reference;

    @FindBy(css = ".product-quantities span")
    private WebElement inStock;

    @FindBy(css = "select.form-control option")
    private List<WebElement> paperTypeOptions;

    @FindBy(css = "select.form-control option")
    private List<WebElement> dimensionOptions;

    @FindBy(css = "div.clearfix label")
    private List<WebElement> colorOptions;

    @FindBy(css = "select.form-control option")
    private List<WebElement> sizeOptions;

    @FindBy(className = "name")
    private List<WebElement> productFeaturesNames;

    @FindBy(className = "value")
    private List<WebElement> productFeaturesValues;

    @FindBy(css = "a[href='#product-details']")
    private WebElement productDetailsButton;

    public List<FullProductItem> getFullProductItemslist(List<ProductItem> products) {

        List<FullProductItem> fullProductItemslist = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            List<String> productPaperTypeOptions = new ArrayList<>();
            List<String> productDimensionOptions = new ArrayList<>();
            List<String> productColorOptions = new ArrayList<>();
            List<String> productSizeOptions = new ArrayList<>();
            Map<String, String> productFeatures = new HashMap<String, String>();

            String variationTitle = null;
            String url = products.get(i).getUrl();
            driver.get(url);
            String productTitle = title.getText();
            float productPrice = Float.parseFloat(price.getText().substring(1));
//            String productShortDescription = shortDescription.getText();
//            String productFullDescription = fullDescription.getText();
//            String productReference = reference.getText();
//            String productInStock = inStock.getText();

            //***Check that variation title is present and set it
            if (isPresent("//form[@id='add-to-cart-or-refresh']/div/div/span[@class='control-label']")) {
                for (int k = 0; k < productVariantsTitle.size(); k++) {
                    variationTitle = productVariantsTitle.get(k).getText();

                    //***Retrieve dimension options
                    if (variationTitle.contains("Dimension")) {
                        if (isPresent("//form[@id='add-to-cart-or-refresh']//option")) {
                            for (int j = 0; j < dimensionOptions.size(); j++) {
                                String option = dimensionOptions.get(j).getText();
                                productDimensionOptions.add(option);
                            }
                        }
                    }

                    //***Retrieve paper type options
                    if (variationTitle.contains("Paper Type")) {
                        if (isPresent("//form[@id='add-to-cart-or-refresh']//option")) {
                            for (int j = 0; j < paperTypeOptions.size(); j++) {
                                String option = paperTypeOptions.get(j).getText();
                                productPaperTypeOptions.add(option);
                            }
                        }
                    }

                    //***Retrieve colors
                    if (variationTitle.contains("Color")) {
                        if (isPresent("//form[@id='add-to-cart-or-refresh']//li/label")) {
                            for (int j = 0; j < colorOptions.size(); j++) {
                                String option = colorOptions.get(j).getText();
                                productColorOptions.add(option);
                            }
                        }
                    }

                    //***Retrieve sizes
                    if (variationTitle.contains("Size")) {
                        if (isPresent("//form[@id='add-to-cart-or-refresh']//option")) {
                            for (int j = 0; j < sizeOptions.size(); j++) {
                                String option = sizeOptions.get(j).getText();
                                productSizeOptions.add(option);
                            }
                        }
                    }
                }
            }
            if (isPresent("//section[@class='product-features']//dt") && isPresent("//section[@class='product-features']//dd")) {
                productDetailsButton.click();

                sleep(2000);

                for (int l = 0; l < productFeaturesNames.size(); l++) {
                    productFeatures.put(productFeaturesNames.get(l).getText(), productFeaturesValues.get(l).getText());
                }
            }


            fullProductItemslist.add(new FullProductItem(
                    productTitle,
                    productPrice,
                    productPaperTypeOptions,
                    productDimensionOptions,
                    productColorOptions,
                    productSizeOptions,
                    productFeatures
            ));
        }
        return fullProductItemslist;
    }

    public boolean isPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }
}
