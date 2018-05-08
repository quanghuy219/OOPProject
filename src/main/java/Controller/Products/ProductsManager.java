package Controller;

import Model.Product.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class ProductsManager {

    private ObservableList<Product> products;
    private String currentProductID;

    public ProductsManager()
    {
        products = FXCollections.observableArrayList();
        currentProductID = "PR0000";

        addProduct(new Book());
        addProduct(new MovieDisc());
        addProduct(new MusicDisc());
        addProduct(new Book());

        addProduct(new Book());
        addProduct(new Book());
        addProduct(new Book());
        addProduct(new Book());
        addProduct(new Book());
        addProduct(new Book());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MovieDisc());
        addProduct(new MusicDisc());
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
        currentProductID = getNextProductID();
        _product.setProductID(currentProductID);
        getProducts().add(_product);
    }

    public void addUpdateProduct(Product _product)
    {
        System.out.println("ID : " + _product);
        System.out.println(products.removeIf(p -> p.getProductID().equals(_product.getProductID())));
        getProducts().add(_product);

//        ObservableList<Product> temp = FXCollections.observableArrayList();
//        System.out.println("Size temp 1: " +temp.size());
//        products.forEach(temp::add);
//        System.out.println("Size temp 2: " +temp.size());
////        products.clear();
//        products.removeAll(products);
//        System.out.println("Size prod 1: " +products.size());
//        temp.forEach(products::add);
//        System.out.println("Size prod 2: " +products.size());

        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int id1 = Integer.valueOf(o1.getProductID().substring(2));
                int id2 = Integer.valueOf(o2.getProductID().substring(2));

                if(id1 == id2) return 0;
                else if(id1 < id2) return -1;
                else return 1;
            }
        });
    }


}
