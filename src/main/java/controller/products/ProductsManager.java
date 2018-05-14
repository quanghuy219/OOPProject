package controller.products;

import model.product.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.Comparator;

public class ProductsManager {

    private ObservableList<Product> products;
    private String currentProductID;

    public ProductsManager() {
        products = FXCollections.observableArrayList();
        currentProductID = "PR0000";

    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void setProducts(ObservableList<Product> products) {
        this.products = products;
    }

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
        updateCurrentProductID(_product.getProductID());
    }

    private void updateCurrentProductID(String newProductID)
    {
        int currentNumber = Integer.valueOf(currentProductID.substring(2));
        int next = Integer.valueOf(newProductID.substring(2));

        if(currentNumber < next)
        {
            currentProductID = newProductID;
        }
    }

    public void addUpdateProduct(Product _product)
    {
        System.out.println("ID : " + _product);
        System.out.println(products.removeIf(p -> p.getProductID().equals(_product.getProductID())));
        getProducts().add(_product);

//        ObservableList<product> temp = FXCollections.observableArrayList();
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

    public Product getProductByID(String _id)
    {
        FilteredList<Product> filteredList = getProducts().filtered(p -> p.getProductID().equals(_id));
        Product result = filteredList.get(0);

//        if(result != null)
//        {
//            System.out.println("result : "+result.getProductID()+" "+result.getCategory());
//        }

        return result;
    }

    public void removeProduct(Product p)
    {
        getProducts().remove(p);
    }
}
