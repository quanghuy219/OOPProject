package Controller;

import Controller.AddProduct.AddBookController;
import Controller.AddProduct.AddMovieDiscController;
import Controller.AddProduct.AddMusicDiscController;
import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.management.modelmbean.ModelMBean;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddProductController implements Initializable {
    private Stage parentStage;
    private Scene parentScene;
    private FileChooser fileChooser;

    @FXML private AnchorPane detailAnchorPane;

    @FXML private JFXTextField idTextField;
    @FXML private JFXTextField nameTextField;
    @FXML private JFXComboBox<String> categoryComboBox;
    @FXML private JFXComboBox<String> statusComboBox;
    @FXML private JFXTextField buyingPriceTextField;
    @FXML private JFXTextField sellingPriceTextField;
    @FXML private JFXTextField quantityTextField;
    @FXML private JFXTextField discountTextField;
    @FXML private JFXComboBox<String> nationComboBox;

    @FXML private ImageView image;

    @FXML private JFXButton fileButton;
    @FXML private JFXButton okButton;
    @FXML private JFXButton cancelButton;

    private FXMLLoader addBookLoader;
    private FXMLLoader addMusicDiscLoader;
    private FXMLLoader addMovieDiscLoader;

    private Node addBookNode;
    private Node addMusicDiscNode;
    private Node addMovieDiscNode;

    private String currentImgUrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentImgUrl = App.defaultPath;

        handleIdTextField();
        handleCategoryComboBox();
        handleStatusComboBox();
        handleNationComboBox();
        handleFileButton();

        handleOkButton();
        handleCancelButton();

        initializeLoaders();

        loadDetailPane(Category.BOOK);

//        try {
//            File f = new File(getClass().getResource("/Image/default.png").toURI());
//            currentImgUrl = f.getAbsolutePath();
//            System.out.println("1: "+currentImgUrl);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

        //File f = new File();
//        Image g = new Image("/Image/default.png");
//        image.setImage(g);
    }

    private void loadDetailPane(Enum<Category> _category)
    {
        detailAnchorPane.getChildren().removeAll(detailAnchorPane.getChildren());

        if(_category == Category.BOOK)
        {
            detailAnchorPane.getChildren().add(addBookNode);

            AddBookController addBookController = addBookLoader.getController();
            addBookController.init(detailAnchorPane);
        }

        else if(_category == Category.MOVIE_DISC)
        {
            detailAnchorPane.getChildren().add(addMovieDiscNode);

            AddMovieDiscController addMovieDiscController = addMovieDiscLoader.getController();
            addMovieDiscController.init(detailAnchorPane);
        }

        else if(_category == Category.MUSIC_DISC)
        {
            detailAnchorPane.getChildren().add(addMusicDiscNode);
            AddMusicDiscController addMusicDiscController = addMusicDiscLoader.getController();
            addMusicDiscController.init(detailAnchorPane);
        }

    }

    private void initializeLoaders()
    {
        addBookLoader = new FXMLLoader(getClass().getResource("/View/Products/AddBook.fxml"));

        addMovieDiscLoader = new FXMLLoader(getClass().getResource("/View/Products/AddMovieDisc.fxml"));
//        AddBookController addBookController = addBookLoader.getController();
//        addBookController.setParentAnchorPane(detailAnchorPane);

        addMusicDiscLoader = new FXMLLoader(getClass().getResource("/View/Products/AddMusicDisc.fxml"));
//        AddBookController addBookController = addBookLoader.getController();
//        addBookController.setParentAnchorPane(detailAnchorPane);

        try {
            addBookNode = addBookLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addMovieDiscNode = addMovieDiscLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            addMusicDiscNode = addMusicDiscLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //addBookController.setParentAnchorPane(detailAnchorPane);
        //addBookController.initialize();

    }

    private void handleIdTextField()
    {
        idTextField.setText(App.dataManager.getProductsManager().getNextProductID());
    }

    private void handleCategoryComboBox()
    {
        String listCategories[] = App.getEnumConstants(Model.Category.class);
        for(String c: listCategories)
        {
            categoryComboBox.getItems().add(c);
        }

        categoryComboBox.setValue(Category.BOOK.toString());

        categoryComboBox.setOnAction(e -> {
            String currentCategory = categoryComboBox.getValue();
            loadDetailPane(Enum.valueOf(Category.class, currentCategory));
        });
    }

    private void handleStatusComboBox()
    {
        String listStatus[] = App.getEnumConstants(Model.Status.class);
        for(String c: listStatus)
        {
            statusComboBox.getItems().add(c);
        }

        statusComboBox.setValue(Status.ACTIVE.toString());
    }

    private void handleNationComboBox()
    {
        String listNations[] = App.getEnumConstants(Model.Nation.class);
        for(String c: listNations)
        {
            nationComboBox.getItems().add(c);
        }

        nationComboBox.setValue(Nation.VIETNAM.toString());
    }

    private void handleOkButton()
    {
        okButton.setOnAction(e -> {

            Boolean selection = App.displayConfirmBox("Are you sure to add this product?");

            if(selection)
            {
                Product result = getProduct();

                App.dataManager.getProductsManager().addProduct(result);

                if(result.getCategory() == Category.BOOK)
                {
                    Book book = (Book) result;
                    book.printDetail();
                }
                else if(result.getCategory() == Category.MOVIE_DISC)
                {
                    MovieDisc movieDisc = (MovieDisc) result;
                    movieDisc.printDetail();
                }
                else
                {
                    MusicDisc musicDisc = (MusicDisc) result;
                    musicDisc.printDetail();
                }

                getParentStage().close();
            }
        });
    }

    private void handleCancelButton()
    {
        cancelButton.setOnAction(e -> {
            Boolean selection = App.displayConfirmBox("Are you sure to cancel adding a new product?");

            if(selection)
            {
                System.out.println("Cancel adding product");
                getParentStage().close();
            }
        });
    }

    private void handleFileButton()
    {
        fileChooser = new FileChooser();

        fileButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(getParentStage());
                    if (file != null) {

                        currentImgUrl = file.getAbsolutePath();
                        System.out.println(currentImgUrl);
                        setImage(file);

//                        try {
//                            saveImage(currentImgUrl, "hung");
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
                    }
                }
            }
        );
    }

    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Choose Picture");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void setImage(File file) {
        Image i = new Image(file.toURI().toString());
//        Image i = new Image(file.getAbsolutePath());
        image.setImage(i);
    }


    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public Product getProduct()
    {
        String currentCategory = categoryComboBox.getValue();

        Product result = new Product();

        result.setProductID(idTextField.getText());
        result.setName(nameTextField.getText());
        result.setCategory(Enum.valueOf(Model.Category.class, categoryComboBox.getValue()));
        result.setStatus(Enum.valueOf(Model.Status.class, statusComboBox.getValue()));
        result.setBuyingPrice(Double.valueOf(buyingPriceTextField.getText()));
        result.setSellingPrice(Double.valueOf(sellingPriceTextField.getText()));
        result.setQuantity(Integer.valueOf(quantityTextField.getText()));
        result.setDiscount(Integer.valueOf(discountTextField.getText()));
        result.setNation(Enum.valueOf(Model.Nation.class, nationComboBox.getValue()));

        try {
            String imgUrl = saveImage(currentImgUrl, result.getProductID());
            result.setImageUrl(imgUrl);
        } catch (IOException e1) {
            e1.printStackTrace();
            result.setImageUrl("default.png");
        }

        if(currentCategory.equals(Category.BOOK.toString()))
        {
            AddBookController addBookController = addBookLoader.getController();
            Book detailedBook = addBookController.getDetailedBook(result);

            return (Product) detailedBook;
        }

        else if(currentCategory.equals(Category.MOVIE_DISC.toString()))
        {
            AddMovieDiscController addMovieDiscController = addMovieDiscLoader.getController();
            MovieDisc detailedMovieDisc = addMovieDiscController.getDetailedMovieDisc(result);

            return (Product) detailedMovieDisc;
        }

        else
        {
            AddMusicDiscController addMusicDiscController = addMusicDiscLoader.getController();
            MusicDisc detailedMusicDisc = addMusicDiscController.getDetailedMovieDisc(result);

            return (Product) detailedMusicDisc;
        }
    }

    private String saveImage(String sourcePath, String destPath) throws IOException {

        if(sourcePath.equals(App.defaultPath))
        {
            return "default.png";
        }

        String relativeImgUrl;

        if(sourcePath.indexOf(".jpg") == (sourcePath.length() - 4))
        {
            destPath = destPath.concat(".jpg");
        }
        else if(sourcePath.indexOf(".png") == (sourcePath.length() - 4))
        {
            destPath = destPath.concat(".png");
        }

        relativeImgUrl = destPath;

        destPath = "src/main/resources/Image/".concat(destPath);

        System.out.println(destPath);

        OutputStream destFile = new FileOutputStream(destPath);

        Files.copy(Paths.get(sourcePath), destFile);

        destFile.close();

        return relativeImgUrl;
    }

    public Scene getParentScene() {
        return parentScene;
    }

    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }



}
