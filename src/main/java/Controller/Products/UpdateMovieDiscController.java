package Controller.UpdateProduct;

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

public class UpdateMovieDiscController {

    private Boolean isClicked = false;

    private AnchorPane parentAnchorPane;
    private JFXTextField[] name;
    private MovieDisc movie;

    @FXML
    private JFXComboBox<Integer> numberComboBox;

    @FXML
    private JFXTextField directorTextField;

    @FXML
    private JFXComboBox<String> languageComboBox;

    @FXML
    private JFXComboBox<String> subtitleComboBox;

    @FXML
    private JFXComboBox<String> genreComboBox;

    @FXML
    private JFXTextField lengthTextField;

    @FXML
    private JFXTextField pointTextField;

    @FXML
    private JFXDatePicker datePicker;

    public void init(AnchorPane _parent, MovieDisc _movie){
        if(!isClicked)
        {
            movie = _movie;
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

        ArrayList<String> actors = movie.getListActors();
        int nActors = actors.size();

        for(int i = 0; i < nActors; i++)
        {
            name[i].setText(actors.get(i));
        }
    }

    private void handleNumberComboBox() {
        for (int i = 1; i <= 5; i++) {
            numberComboBox.getItems().add(i);
        }

        int nActhors = movie.getListActors().size();

        numberComboBox.setValue(nActhors);

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
            subtitleComboBox.getItems().add(c);
        }

        languageComboBox.setValue(movie.getLanguage().toString());
        subtitleComboBox.setValue(movie.getSubtitle().toString());
    }

    private void handleGenreComboBox() {
        String listGenre[] = App.getEnumConstants(Model.MovieGenre.class);
        for (String c : listGenre) {
            genreComboBox.getItems().add(c);
        }

        genreComboBox.setValue(movie.getGenre().toString());
    }

    private void handleDatePicker() {
        datePicker.setValue(movie.getPublicDate());
    }

    public void setParentAnchorPane(AnchorPane _parent) {
        parentAnchorPane = _parent;
    }

    public AnchorPane getParentAnchorPane() {
        return parentAnchorPane;
    }

    public MovieDisc getDetailedMovieDisc(Product _product) {
        ArrayList<String> _listActors = new ArrayList<String>();
        String _director;
        Enum<Language> _language;
        Enum<Language> _subtitle;
        Enum<MovieGenre> _genre;
        int _length;
        float _point;
        LocalDate _publicDate;

        MovieDisc result = new MovieDisc(_product);

        for (int i = 0; i < numberComboBox.getValue(); i++) {
            _listActors.add(name[i].getText());
        }

        _director = directorTextField.getText();
        _language = Enum.valueOf(Model.Language.class, languageComboBox.getValue());
        _subtitle = Enum.valueOf(Model.Language.class, subtitleComboBox.getValue());
        _genre = Enum.valueOf(Model.MovieGenre.class, genreComboBox.getValue());
        _length = Integer.valueOf(lengthTextField.getText());
        _point = Float.valueOf(pointTextField.getText());
        _publicDate = datePicker.getValue();

        result.setListActors(_listActors);
        result.setDirector(_director);

        result.setLanguage(_language);
        result.setSubtitle(_subtitle);

        result.setGenre(_genre);
        result.setLength(_length);
        result.setImdbPoint(_point);
        result.setPublicDate(_publicDate);

        return result;
    }
}
