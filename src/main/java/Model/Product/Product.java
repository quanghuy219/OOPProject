package Model;

import java.sql.SQLOutput;

public class Product {
    private String productID;
    private String name;
    private Enum<Category> category;
    private Enum<Status> status;

    private int quantity;

    private double sellingPrice;
    private double buyingPrice;

    private Enum<Nation> nation;

    private String imageUrl;
    private int discount;


    public Product(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount)
    {
        this.setProductID(_productID);
        this.setName(_name);
        this.setCategory(_category);
        this.setStatus(_status);
        this.setQuantity(_quantity);

        this.setSellingPrice(_sellingPrice);
        this.setBuyingPrice(_buyingPrice);
        this.setNation(_nation);

        this.setImageUrl(_imageUrl);
        this.setDiscount(_discount);
    }

    public Product()
    {
        this.setProductID("PR0001");
        this.setName("");
        this.setCategory(Category.BOOK);
        this.setStatus(Status.ACTIVE);
        this.setQuantity(0);

        this.setSellingPrice(0);
        this.setBuyingPrice(0);
        this.setNation(Nation.UNITED_KINGDOM);

        this.setImageUrl("default.png");
        this.setDiscount(0);
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

    public Enum<Category> getCategory() {
        return category;
    }

    public void setCategory(Enum<Category> category) {
        this.category = category;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Enum<Nation> getNation() {
        return nation;
    }

    public void setNation(Enum<Nation> nation) {
        this.nation = nation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void printDetail()
    {
        System.out.println("ID: "+getProductID());
        System.out.println("Name: "+getName());
        System.out.println("Category: "+getCategory().toString());
        System.out.println("Status: "+getStatus().toString());
        System.out.println("Quantity: "+getQuantity());

        System.out.println("BuyPrice: "+getBuyingPrice());
        System.out.println("SellPrice: "+getSellingPrice());
        System.out.println("Nation: "+getNation().toString());
        System.out.println("Img: "+getImageUrl());
        System.out.println("Discount: "+getDiscount());
    }
}


