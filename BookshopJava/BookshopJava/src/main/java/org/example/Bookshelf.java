package org.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bookshelf {
    private Collection<Book> bookCollection = new ArrayList<Book>();

    public Bookshelf() {}

    public Collection<Book> getBookCollection()
    {
        return bookCollection;
    }

    public void addBook(Book book)
    {
        bookCollection.add(book);
    }

    public void deleteBook(Book book) {bookCollection.remove(book);}
    public void sortBookCollection() {
        List<Book> sortedBooks = new ArrayList<>(bookCollection);
        Collections.sort(sortedBooks);

        bookCollection.clear();
        bookCollection.addAll(sortedBooks);
    }
    public Book getBookByName(String name) {
        for (Book book : bookCollection) {
            if (book.getTitle().equals(name)) {
                return book;
            }
        }
        return null;
    }
    public Book getBookByID(int id) {
        for (Book book : bookCollection) {
            if (book.getId()==id) {
                return book;
            }
        }
        return null;
    }
    public ArrayList<Book> getAllBooksByAuthor(String author) {
        ArrayList<Book> allBooksByAuthor= new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getAuthor().equals(author)) {
                allBooksByAuthor.add(book);
            }
        }
        if (!allBooksByAuthor.isEmpty())
            return allBooksByAuthor;
        return null;
    }
}
