package servs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <h1>Book</h1>
 * <p>
 * This code is for the book entity. The main component of the library.</p>
 *
 * @author Olivier
 * @version 0
 * @since 2019-05-25
 *
 */
@XmlRootElement(name = "book")

public class Book implements Serializable, Comparable <Book> {

    String title;
    String description;
    String isbn;
    String author;
    String publisher;

    /**
     * <p>
     * This constructor is the only way for creating a book entity. This
     * constructor is used only by the book repository class. The book are
     * created and modified automatically by using the methods from the book
     * repository class.</p>
     *
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     */
    public Book(String title, String description, String isbn, String author, String publisher) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
    }

    /**
     *
     * Not used.
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Not used.
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Not used.
     * @return
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Not used.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Not used.
     * @return
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Not used.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Not used.
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Not used.
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Not used.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Not used.
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int compareTo(Book o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
