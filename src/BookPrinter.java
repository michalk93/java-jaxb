import model.Book;
import model.Title;

/**
 * Created by mkolbusz on 4/13/16.
 */
public class BookPrinter {
    public void print(Book book){
        for(Title title : book.getTitles()) {
            System.out.println("Tytuł w jezyku "+title.getLang()+": "+title.getTitle());
        }
        System.out.print("Autorzy: ");
        for(String author : book.getAuthors()){
            System.out.print(author+"; ");
        }
        System.out.println("\nISBN: " + book.getIsbn()+"\n");
    }
}
