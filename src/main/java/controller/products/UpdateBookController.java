package controller.products;

import controller.App;
import model.product.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import model.product.Book;
import model.product.BookGenre;
import model.product.Language;
import model.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateBookController {

    private Boolean isClicked = false;

    private AnchorPane parentAnchorPane;
    private JFXTextField[] name;

    @FXML
    private JFXComboBox<Integer> numberComboBox;

    @FXML
    private JFXComboBox<String> languageComboBox;
    @FXML
    private JFXComboBox<String> genreComboBox;
    @FXML
    private JFXTextField lengthTextField;
    @FXML
    private JFXDatePicker datePicker;

    private Book book;

    public void init(AnchorPane _parent, Book _book){
        if(!isClicked)
        {
            book = _book;
            isClicked = true;
            setParentAnchorPane(_parent);
            handleNameTextFields();
            handleNumberComboBox();
            handleLanguageComboBox();
            handleDatePicker();
            handleGenreComboBox();
        }
    }

    private void handleNameTextFields() {
        name = new JFXTextField[5];

        for (int i = 0; i < 5; i++) {
            name[i] =  (JFXTextField) getParentAnchorPane().lookup("#name" + (i+1));
        }

        ArrayList<String> authors = book.getListAuthors();
        int nAuthors = authors.size();

        for(int i = 0; i < nAuthors; i++)
        {
            name[i].setText(authors.get(i));
        }
    }

    private void handleNumberComboBox() {
        for (int i = 1; i <= 5; i++) {
            numberComboBox.getItems().add(i);
        }

        int nAuthors = book.getListAuthors().size();

        numberComboBox.setValue(nAuthors);

        numberComboBox.setOnAction(e -> {
            int currentValue = numberComboBox.getValue();

            for (int i = 0; i < 5; i++) {
                if (i >= currentValue) {
                    name[i].setDisable(true);
                } else
                    name[i].setDisable(false);
            }
        });
    }

    private void handleLanguageComboBox() {
        String listLanguages[] = App.getEnumConstants(Language.class);
        for (String c : listLanguages) {
            languageComboBox.getItems().add(c);
        }

        languageComboBox.setValue(book.getLanguage().toString());
    }

    private void handleGenreComboBox() {
        String listGenre[] = App.getEnumConstants(BookGenre.class);
        for (String c : listGenre) {
            genreComboBox.getItems().add(c);
        }

        genreComboBox.setValue(book.getGenre().toString());
    }

    private void handleDatePicker() {
        datePicker.setValue(book.getPublicDate());
    }

    public void setParentAnchorPane(AnchorPane _parent) {
        parentAnchorPane = _parent;
    }

    public AnchorPane getParentAnchorPane() {
        return parentAnchorPane;
    }

    public Book getDetailedBook(Product _product) {
        ArrayList<String> _listAuthors = new ArrayList<String>();

        Enum<Language> _language;
        Enum<BookGenre> _genre;
        int _length;
        LocalDate _publicDate;

        Book result = new Book(_product);

        for (int i = 0; i < numberComboBox.getValue(); i++) {
            _listAuthors.add(name[i].getText());
        }

        _language = Enum.valueOf(Language.class, languageComboBox.getValue());
        _genre = Enum.valueOf(BookGenre.class, genreComboBox.getValue());
        _length = Integer.valueOf(lengthTextField.getText());
        _publicDate = datePicker.getValue();

        result.setListAuthors(_listAuthors);
        result.setGenre(_genre);
        result.setLanguage(_language);
        result.setLength(_length);
        result.setPublicDate(_publicDate);

        return result;
    }
}
