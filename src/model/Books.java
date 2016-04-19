package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 4/12/16.
 */
@XmlRootElement(name = "books")
public class Books {

    private List<Book> booksList = new ArrayList<>();

    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return booksList;
    }

    public void setBooks(List<Book> books) {
        this.booksList = books;
    }

    public void addBook(Book book){
        booksList.add(book);
    }
}
