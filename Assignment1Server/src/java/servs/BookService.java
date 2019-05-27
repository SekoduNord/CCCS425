package servs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * <h1>Book service</h1>
 * <p>
 * This code is used to access the library.</p>
 *
 * @author Olivier
 * @version 1
 * @since 2019-05-26
 *
 */
@Path("/")
public class BookService {

    @Context
    private ServletContext sctx;

    BookRepository repo = new BookRepository();

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
     * @param servletResponse
     * @param author
     * @param publisher
     */
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/addBook/")
    public void addBook(
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("isbn") String isbn,
            @FormParam("author") String author,
            @FormParam("publisher") String publisher,
            @Context HttpServletResponse servletResponse) {
        repo.addBook(title, description, isbn, author, publisher);
    }

    /**
     *         * <p>
     * The getBook method is used to request a list of all books inside the
     * library.
     * </p>
     *
     * @return
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/getBook/")
    public String getBook() {

        return repo.getBook();
    }

    /**
     *         * <p>
     * The getBookId method is used to request a specific book inside the
     * library. The primary key for this book must be specified as a parameter.
     * </p>
     *
     * @param id
     * @return
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/getBookId/{id:\\d+}")
    public String getBookId(@PathParam("id") int id) {

        return repo.getBookId(id);
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
     * @param servletResponse
     * @param isbn
     * @param author
     * @param publisher
     * @param description
     */
    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/updateBook/")
    public void updateBook(
            @FormParam("id") int id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("isbn") String isbn,
            @FormParam("author") String author,
            @FormParam("publisher") String publisher,
            @Context HttpServletResponse servletResponse) {

        repo.updateBook(id, title, description, isbn, author, publisher);
    }

    /**
     * <p>
     * The deleteBook method is used to delete a specific book inside the
     * library. The primary key for this book must be specified as a parameter.
     * </p>
     *
     * @param id
     * @param servletResponse
     */
    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/deleteBook/")
    public void deleteBook(@FormParam("id") String id,
            @Context HttpServletResponse servletResponse) {

        repo.deleteBook(id);
    }

    /**
     * <p>
     * The getHello method is used to test the server in the most simple way
     * possible. A string is send to the user when the server is running.
     * </p>
     *
     * @return
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/hello")
    public String getHello() {

        return "Hello I am a Server! \n";
    }
}

/**
 *
 * <p>
 * Some curl command example.
 * </p>
 *
 */
//curl command
//(In browser) http://localhost:8084/Module2Activity1/webresources/getBook
//curl --request POST --data "info=21,22,23" http://localhost:8084/Module2Activity1/webresources/addBook
//curl --request PUT --data "info=4,T55,A55,I55" http://localhost:8084/Module2Activity1/webresources/updateBook/
//curl --request DELETE --data "info=2" http://localhost:8084/Module2Activity1/webresources/deleteBook/
