package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Book extends Product {
    private ArrayList<String> listAuthors;

    private Enum<Language> language;
    private Enum<BookGenre> genre;
    private int length;
    private LocalDate publicDate;

    public Book(String _productID, String _name, Enum<Category> _category, Enum<Status> _status, int _quantity, double _buyingPrice, double _sellingPrice, Enum<Nation> _nation, String _imageUrl, int _discount, ArrayList<String> _listAuthors, Enum<Language> _language, Enum<BookGenre> _genre, int _length, LocalDate _publicDate)
    {
        super(_productID, _name, _category, _status, _quantity, _buyingPrice, _sellingPrice, _nation, _imageUrl, _discount);

        this.setListAuthors(_listAuthors);
        this.setLanguage(_language);
        this.setGenre(_genre);
        this.setLength(_length);
        this.setPublicDate(_publicDate);
    }

    public Book(Product _product)
    {
        super(_product.getProductID(), _product.getName(), _product.getCategory(), _product.getStatus(), _product.getQuantity(), _product.getBuyingPrice(), _product.getSellingPrice(), _product.getNation(), _product.getImageUrl(), _product.getDiscount());
    }

    public Book()
    {
        super();
        this.setListAuthors(new ArrayList<String>(Arrays.asList("", "", "", "", "")));
        this.setLanguage(Language.ENGLISH);
        this.setGenre(BookGenre.THRILLER);
        this.setLength(0);
        this.setPublicDate(LocalDateTime.now().toLocalDate());
    }

    public ArrayList<String> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(ArrayList<String> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public Enum<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Enum<Language> language) {
        this.language = language;
    }

    public Enum<BookGenre> getGenre() {
        return genre;
    }

    public void setGenre(Enum<BookGenre> genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        System.out.println("List Authors: "+getListAuthors());
        System.out.println("Language: "+getLanguage().toString());
        System.out.println("Genre: "+getGenre().toString());
        System.out.println("Length: "+getLength());
        System.out.println("Date: "+getPublicDate().toString());
    }
}
