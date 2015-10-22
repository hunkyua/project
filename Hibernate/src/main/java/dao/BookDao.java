package dao;

import table.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oktopus on 22.10.15.
 */
public interface BookDao {
    public void addBook(Book book) throws SQLException;

    public void deleteBook(Book book) throws SQLException;

    public Book getBook(int id) throws SQLException;

    public List<Book> getBooks() throws SQLException;
}
