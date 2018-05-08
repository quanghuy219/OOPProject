package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class ProductsManager {

    private ObservableList<Product> products;
    private String currentProductID;

    public ProductsManager()
    {
        products = FXCollections.observableArrayList();
//        getProducts().add(new Book("1", "ABC", "Book", "Vietnam", 342,12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
//        getProducts().add(new Book("2", "ABCD", "Book", "China", 12, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));
//        getProducts().add(new Book("3", "ABCE", "Book", "Germany", 23, 12000, 10000, null, "English", 69, new Date(1998, 11, 12)));

        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product());
        addProduct(new Product("PR0100", "Hihi", Category.BOOK, Status.ACTIVE, 100, 1000000, 1200000, Nation.UNITED_KINGDOM, null, 0));

        //getNextProductID();
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }

//    public ObservableList<Product> search(String _idOrName, String _category, String _nation, boolean _isDeleted)
//    {
//        ObservableList<Product> result;
//
//        if(_isDeleted)
//        result = products.filtered(p -> p.isActive() && p.getCategory().equals(_category) && p.getNation().equals(_nation) && p.getName().contains(_idOrName) || p.getProductID().contains(_idOrName));
//        return result;
//    }

    public String getNextProductID()
    {
        int currentNumber = Integer.valueOf(currentProductID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);
        //System.out.println("length " + nextNumber.length());
        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextProductID = "PR".concat(nextNumber);
        return nextProductID;
    }

    public void addProduct(Product _product)
    {
        getProducts().add(_product);
        currentProductID = _product.getProductID();
    }


}
