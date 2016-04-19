package model;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 4/12/16.
 */

public class Book {

    private List<String> authors = new ArrayList<>();
    private List<Title> titles = new ArrayList<>();
    private String isbn;

    @XmlElement(name = "author")
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @XmlElement(name = "title")
    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void addAuthor(String author){
        this.authors.add(author);
    }

    public void addTitle(Title title) {
        this.titles.add(title);
    }

    @Override
    public String toString() {
        return "Autorzy: " + authors + "\nTytuły: " + titles + "\n Numer ISBN: " + isbn;
    }
}
