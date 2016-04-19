import model.Book;
import model.Books;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

/**
 * Created by mkolbusz on 4/13/16.
 */
public class Main_Unmarshaller {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Books.class);
            Unmarshaller u = context.createUnmarshaller();
            u.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("books.xsd")));


            Books books = (Books)u.unmarshal(new File("books.xml"));

            BookPrinter printer = new BookPrinter();
            for(Book book : books.getBooks()){
                printer.print(book);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
