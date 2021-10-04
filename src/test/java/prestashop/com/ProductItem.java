package prestashop.com;

public class ProductItem {
    private String title;
    private String price;
//    public float regularPrice;

    public ProductItem(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
