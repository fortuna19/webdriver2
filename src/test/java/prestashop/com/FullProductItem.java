package prestashop.com;

import java.util.Collections;
import java.util.List;

public class FullProductItem {
    private String title;
    private float price;
    private List<String> paperType;
    private List<String> dimension;
    private List<String> colors;
    private List<String> sizes;

    public FullProductItem(String title, float price, List<String> paperType, List<String> dimension, List<String> colors, List<String> sizes) {
        this.title = title;
        this.price = price;
        this.paperType = paperType;
        this.dimension = dimension;
        this.colors = colors;
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return "FullProductItem{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", paperType=" + paperType +
                ", dimension=" + dimension +
                ", colors=" + colors +
                ", sizes=" + sizes +
                '}';
    }
}
