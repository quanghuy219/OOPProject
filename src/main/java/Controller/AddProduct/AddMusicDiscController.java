package Controller.AddProduct;

import Controller.App;
import Model.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddMusicDiscController {

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
    private JFXDatePicker datePicker;

    public void init(AnchorPane _parent){
        if(!isClicked)
        {
            isClicked = true;
            setParentAnchorPane(_parent);
            setNameTextFields();
            handleNumberComboBox();
            handleLanguageSubtitleComboBox();
            handleDatePicker();
            handleGenreComboBox();
        }
    }

    private void setNameTextFields() {
        name = new JFXTextField[5];

        for (int i = 0; i < 5; i++) {
            name[i] =  (JFXTextField) getParentAnchorPane().lookup("#name" + (i+1));
        }
    }

    private void handleNumberComboBox() {
        for (int i = 1; i <= 5; i++) {
            numberComboBox.getItems().add(i);
        }

        numberComboBox.setValue(5);

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

    private void handleLanguageSubtitleComboBox() {
        String listLanguages[] = App.getEnumConstants(Model.Language.class);
        for (String c : listLanguages) {
            languageComboBox.getItems().add(c);
        }

        languageComboBox.setValue(Language.ENGLISH.toString());
    }

    private void handleGenreComboBox() {
        String listGenre[] = App.getEnumConstants(Model.MusicGenre.class);
        for (String c : listGenre) {
            genreComboBox.getItems().add(c);
        }

        genreComboBox.setValue(MusicGenre.COUNTRY.toString());
    }

    private void handleDatePicker() {
        datePicker.setValue(LocalDateTime.now().toLocalDate());
    }



    public void setParentAnchorPane(AnchorPane _parent) {
        parentAnchorPane = _parent;
    }

    public AnchorPane getParentAnchorPane() {
        return parentAnchorPane;
    }

    public MusicDisc getDetailedMovieDisc(Product _product) {
        List<String> _listSingers = new ArrayList<String>();
        Enum<Language> _language;
        Enum<MusicGenre> _genre;
        LocalDate _publicDate;

        MusicDisc result = new MusicDisc(_product);

        for (int i = 0; i < numberComboBox.getValue(); i++) {
            _listSingers.add(name[i].getText());
        }

        _language = Enum.valueOf(Model.Language.class, languageComboBox.getValue());

        _genre = Enum.valueOf(Model.MusicGenre.class, genreComboBox.getValue());
        _publicDate = datePicker.getValue();

        result.setListSingers(_listSingers);
        result.setLanguage(_language);
        result.setGenre(_genre);
        result.setPublicDate(_publicDate);

        return result;
    }

    public void print()
    {
        System.out.println("hehe");
    }
}
