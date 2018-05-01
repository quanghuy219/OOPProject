package Model;

public class Product {
    private String productID;
    private String name;
    private String category;
    private int remaining;
    private String nation;
    private double salesPrice;
    private double importPrice;

    private String imageUrl;
    private boolean isActive;

    public Product(String _productID, String _name, String _category, String _nation, int _remaining, double _salesPrice, double _receivedPrice)
    {
        this.setProductID(_productID);
        this.setName(_name);
        this.setRemaining(_remaining);
        this.setSalesPrice(_salesPrice);
        this.setImportPrice(_receivedPrice);
        this.setCategory(_category);
        this.setNation(_nation);
    }

    public Product()
    {
        this.setProductID("PR00001");
        this.setName("ABC");
        this.setRemaining(129);
        this.setSalesPrice(1000000);
        this.setImportPrice(1200000);
        this.setCategory("Movie");
        this.setNation("Laos");
        this.setActive(true);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
