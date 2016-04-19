import model.Book;
import model.Books;
import model.Title;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.util.*;
import java.io.File;

/**
 * Created by mkolbusz on 4/12/16.
 */
public class Main_Marshaller {

    static Books books = new Books();

    public static void main(String[] args) {

        File file = new File("books.xml");
        if(file.exists()){
            JAXBContext context = null;
            try {
                context = JAXBContext.newInstance(Books.class);
                Unmarshaller u = context.createUnmarshaller();
                u.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("books.xsd")));
                books = (Books)u.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        }

        collectData();

        try {
            JAXBContext context = JAXBContext.newInstance(Books.class);
            final File outFile = new File("books.xsd");
            try {
                context.generateSchema(new SchemaOutputResolver() {
                    @Override
                    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                        StreamResult result = new StreamResult(outFile);
                        result.setSystemId(outFile.toURI().toURL().toString());
                        return result;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "books.xsd");

            m.marshal(books, System.out);

            m.marshal(books, new File("books.xml"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    private static void collectData(){
        Scanner scanner = new Scanner(System.in);
        String input;

        do{
            System.out.println("Czy wprowadzić nową książkę?");
            if(scanner.nextLine().compareToIgnoreCase("nie") == 0){
                break;
            }
            Book book = new Book();

            do {
                System.out.println("Podaj autora książki lub ENTER aby zakończyć: ");
                if((input = scanner.nextLine()).isEmpty()){
                    break;
                }
                book.addAuthor(input);
            }while(true);

            do {
                Title title = new Title();
                System.out.println("Podaj język tytułu lub ENTER aby zakończyć: ");
                if((input = scanner.nextLine()).isEmpty()){
                    break;
                }
                title.setLang(input);
                System.out.println("Podaj tytułu w tym jezyku: ");
                title.setTitle(scanner.nextLine());
                book.addTitle(title);
            }while(true);

            System.out.println("Podaj numer ISBN ksiżąki: ");
            book.setIsbn(scanner.nextLine());

            System.out.println("-----");
            System.out.println(book);
            System.out.println("Czy dane są poprawne?");
            input = scanner.nextLine();
            if(input.compareToIgnoreCase("tak") == 0){
                books.addBook(book);
            }
        }while(true);
    }
}
