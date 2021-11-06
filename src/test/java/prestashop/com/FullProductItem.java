package prestashop.com;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class FullProductItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private float price;
    private List<String> paperType;
    private List<String> dimension;
    private List<String> colors;
    private List<String> sizes;
    private Map<String, String> features;

    public FullProductItem(String title, float price, List<String> paperType, List<String> dimension, List<String> colors, List<String> sizes, Map<String, String> features) {
        this.title = title;
        this.price = price;
        this.paperType = paperType;
        this.dimension = dimension;
        this.colors = colors;
        this.sizes = sizes;
        this.features = features;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getPaperType() {
        return paperType;
    }

    public void setPaperType(List<String> paperType) {
        this.paperType = paperType;
    }

    public List<String> getDimension() {
        return dimension;
    }

    public void setDimension(List<String> dimension) {
        this.dimension = dimension;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
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
                ", features=" + features +
                '}';
    }
}
