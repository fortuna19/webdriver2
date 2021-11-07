package prestashop.com.pages;

public class ProductItem {
    private String title;
    private double price;
    private String url;

    public ProductItem(String title, double price, String url) {
        this.title = title;
        this.price = price;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
