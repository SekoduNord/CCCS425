package servs;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>Book repository</h1>
 * <p>
 * This code is for interacting with the book entity. The book service class
 * must be use to access this code</p>
 *
 * @author Olivier
 * @version 1
 * @since 2019-05-26
 *
 */
public class BookRepository {

    private static ConcurrentHashMap<Integer, Book> books = new ConcurrentHashMap<>();
    private static int key;

    /**
     * <p>
     * The addBook method is used to add a new book to the library. The format
     * for this command is title, description, isbn, author, publisher. All
     * parameters must be separated with a comma.
     * </p>
     *
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     * @param info
     */
    public void addBook(String title, String description, String isbn,
            String author, String publisher) {
        /**
         *
         *
         *
         */

        books.put(findNextKey(), new Book(title, description, isbn, author, publisher));
    }

    /**
     * <p>
     * The getBook method is used to request a list of all books inside the
     * library.
     * </p>
     *
     * @return
     */
    public String getBook() {
        /**
         *
         *
         *
         */
        String result = "";
        for (int i = 0; i <= key; i++) {
            if (books.get(i) != null) {
                result += "key: " + i + " value: " + books.get(i).title + "\t" + books.get(i).description + "\t" + books.get(i).isbn + "\t" + books.get(i).author + "\t" + books.get(i).publisher + "\n";
            }
        }
        if (result.equals("")) {
            return "Library empty";
        } else {
            return result;
        }
    }

    /**
     * <p>
     * The getBookId method is used to request a specific book inside the
     * library. The primary key for this book must be specified as a parameter.
     * </p>
     *
     * @param id
     * @return
     */
    public String getBookId(int id) {
        /**
         *
         *
         *
         */
        if (books.get(id) == null) {
            return "Book not found";
        } else {
            return books.get(id).title + "\t" + books.get(id).description + "\t" + books.get(id).isbn + "\t" + books.get(id).author + "\t" + books.get(id).publisher;
        }
    }

    /**
     * <p>
     * The updateBook method is used to modify a specific book inside the
     * library. The primary key and all other parameters for this book must be
     * specified as a parameter. The book parameters are title, description,
     * isbn, author, publisher. All parameters must be separated with a
     * comma.</p>
     *
     * @param id
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     */
    public void updateBook(int id, String title, String description, String isbn,
            String author, String publisher) {
        /**
         *
         *
         */

        books.replace(id, new Book(title, description, isbn, author, publisher));
    }

    /**
     * <p>
     * The deleteBook method is used to delete a specific book inside the
     * library. The primary key for this book must be specified as a parameter.
     * </p>
     *
     * @param idString
     */
    public void deleteBook(String idString) {
        /**
         *
         *
         */

        int id = Integer.parseInt(idString);
        books.remove(id);
    }

    /**
     *
     * <p>
     * FindNextKey is used to find the next free primary key for adding new
     * books to the library.
     * </p>
     *
     */
    private static int findNextKey() {

        if (books.isEmpty()) {
            key = 0;
        } else {
            key += 1;
        }
        return key;

    }

}
