package Controller;

public class DataManager {
    private ProductsManager productsManager;


    public DataManager()
    {
        setProductsManager(new ProductsManager());
    }

    public ProductsManager getProductsManager() {
        return productsManager;
    }

    public void setProductsManager(ProductsManager productsManager) {
        this.productsManager = productsManager;
    }
}
