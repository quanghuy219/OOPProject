package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MusicDisc extends Product {

    private List<String> listSingers;
    private Enum<Language> language;
    private Enum<MusicGenre> genre;
    private LocalDate publicDate;

    public MusicDisc(Product _product)
    {
        super(_product.getProductID(), _product.getName(), _product.getCategory(), _product.getStatus(), _product.getQuantity(), _product.getBuyingPrice(), _product.getSellingPrice(), _product.getNation(), _product.getImageUrl(), _product.getDiscount());
    }

    public MusicDisc(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, List<String> _listSingers, Enum<Language> _language, Enum<MusicGenre> _genre, LocalDate _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);

        this.setListSingers(_listSingers);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setPublicDate(_publicDate);

    }

    public List<String> getListSingers() {
        return listSingers;
    }

    public void setListSingers(List<String> listSingers) {
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
