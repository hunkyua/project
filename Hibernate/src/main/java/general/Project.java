package general;

import dao.BookDao;
import table.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oktopus on 22.10.15.
 */
public class Project {
    public static void main(String[] args) throws SQLException {
        Factory factory = Factory.getInstance();
        BookDao bookDao = factory.getBookDao();

        List<Book> books = bookDao.getBooks();


        System.out.println("id   isbn   title          description");
        for(Book book: books) {

            System.out.println(book.getId() + "    "
                    + book.getIsbn() + "   "
                    + book.getTitle() + "  "
                    + book.getDescription());
        }
    }
}
