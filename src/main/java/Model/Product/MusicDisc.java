package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MusicDisc extends Product {

    private ArrayList<String> listSingers;
    private Enum<Language> language;
    private Enum<MusicGenre> genre;
    private LocalDate publicDate;

    public MusicDisc()
    {
        super();
        this.setCategory(Category.MUSIC_DISC);

        this.setListSingers(new ArrayList<String>(Arrays.asList("", "", "", "", "")));
        this.setLanguage(Language.ENGLISH);
        this.setGenre(MusicGenre.COUNTRY);
        this.setPublicDate(LocalDateTime.now().toLocalDate());
    }

    public MusicDisc(Product _product)
    {
        super(_product.getProductID(), _product.getName(), _product.getCategory(), _product.getStatus(), _product.getQuantity(), _product.getBuyingPrice(), _product.getSellingPrice(), _product.getNation(), _product.getImageUrl(), _product.getDiscount());
        this.setCategory(Category.MUSIC_DISC);
    }

    public MusicDisc(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, ArrayList<String> _listSingers, Enum<Language> _language, Enum<MusicGenre> _genre, LocalDate _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);
        this.setCategory(Category.MUSIC_DISC);
        this.setListSingers(_listSingers);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setPublicDate(_publicDate);

    }

    public ArrayList<String> getListSingers() {
        return listSingers;
    }

    public void setListSingers(ArrayList<String> listSingers) {
        this.listSingers = listSingers;
    }

    public Enum<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Enum<Language> language) {
        this.language = language;
    }

    public Enum<MusicGenre> getGenre() {
        return genre;
    }

    public void setGenre(Enum<MusicGenre> genre) {
        this.genre = genre;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public void printDetail()
    {
        super.printDetail();

        System.out.println("Singers: "+getListSingers());
        System.out.println("Language: "+getLanguage());
        System.out.println("Genre: "+getGenre().toString());
        System.out.println("Date: "+getPublicDate());
    }
}
