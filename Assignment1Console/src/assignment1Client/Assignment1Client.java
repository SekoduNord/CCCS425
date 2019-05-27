
package assignment1Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * <h1>Assignment 1 - Client</h1>
 * <p>This code is the client side for the server application. This code run a console application with all the
 * functionality accessible by a quick menu.</p>
 * 
 * @author Olivier
 * @version 2
 * @since   2019-05-26
 * 
 */

public class Assignment1Client {

    private final String USER_AGENT = "Mozilla/5.0";
    private final static String url = "http://localhost:8084/Assignment1Server/webresources/";

    /**
     *
     * @param args not used
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        //Application menu
        System.out.println("Select a function:\n"
                + "1-Help, 2-List, 3-Display, 4-Add, 5-Update, 6-Delete, 7-Quit");
        String entry = in.nextLine();
        String[] buffer = {"Title", "Description", "Isbn", "Author", "Publisher"};

        while (!entry.equals("7")) {
            switch (entry) {
                case "1":
                    System.out.println("Help selected");
                    help();
                    break;

                case "2":
                    System.out.println("List all books");
                    list();
                    break;

                case "3":
                    System.out.println("Display one book \n"
                            + "Enter id requested");
                    try {
                        int id = in.nextInt();
                        display(id);
                    } catch (Exception e) {
                        System.out.println("Wrong entry format or book not found");
                    }
                    entry = in.nextLine();
                    break;

                case "4":
                    System.out.println("Add a book \n"
                            + "Enter book info (Title,Description,ISBN,Author,Publisher)");
                    entry = in.nextLine();
                    buffer = entry.split(",");
                    try {
                        add(buffer[0], buffer[1], buffer[2], buffer[3], buffer[4]);
                    } catch (Exception e) {
                        System.out.println("Wrong entry format");
                    }
                    break;

                case "5":
                    System.out.println("Update one book \n"
                            + "Enter book info (Id, Title,Description,ISBN,Author,Publisher)");
                    entry = in.nextLine();
                    buffer = entry.split(",");
                    try {
                        update(Integer.parseInt(buffer[0]), buffer[1], buffer[2], buffer[3], buffer[4], buffer[5]);
                    } catch (Exception e) {
                        System.out.println("Wrong entry format or book not found");
                    }
                    break;

                case "6":
                    System.out.println("Delete one book \n"
                            + "Enter id requested");
                    try {
                        int id = in.nextInt();
                        delete(id);
                    } catch (Exception e) {
                        System.out.println("Wrong entry format or book not found");
                    }
                    entry = in.nextLine();
                    break;

                default:
                    System.out.println("Wrong entry");
            }
            System.out.println("Select a function:\n"
                    + "1-Help, 2-List, 3-Display, 4-Add, 5-Update, 6-Delete, 7-Quit");
            entry = in.nextLine();

        }
        System.out.println("Exit selected");
        quit();
    }

    /**
     *Help is used to give some information to the user about the different functionality and how to use them.
     * There is no HTTP command in this command
     */
    public static void help() {
        System.out.println("This applictation use the server located at \n"
                + "http://localhost:8084/Assignment1Server to interact with a books library. \n"
                + "Enter the number corresponding to the function desired and press enter \n"
                + "or enter 7 to quit the application. \n");
    }

    /**
     * 
     *List is used to request a list of all books currently inside the library.
     * This an HTTP GET command
     */
    public static void list() {
        Assignment1Client http = new Assignment1Client();
        try {
            http.sendGet(url + "getBook/");
            System.out.println("OK");
        } catch (Exception e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");
        }
    }

    /**
     * 
     * Display is used to display a specific book from the library. The primary for this book must be supplied.
     * This an HTTP GET command
     * @param id primary for the book requested
     */
    public static void display(int id) {
        Assignment1Client http = new Assignment1Client();
        try {
            http.sendGet(url + "getBookId/" + id);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");
        }
    }

    /**
     * Add is for adding a book to the library. All parameters for this book must be supplied.
     * This an HTTP POST command
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     */
    public static void add(String title, String description, String isbn, String author, String publisher) {
        Assignment1Client http = new Assignment1Client();
        try {
            http.sendPost(url + "addBook/", title, description, isbn, author, publisher);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");
        }
    }

    /**
     * Update is for modifying a book in the library. The primary key and all the parameters for this book must be supplied.
     * This an HTTP PUT command
     * @param id
     * @param title
     * @param description
     * @param isbn
     * @param author
     * @param publisher
     */
    public static void update(int id, String title, String description, String isbn, String author, String publisher) {
        Assignment1Client http = new Assignment1Client();
        try {
            http.sendPut(url + "updateBook/", id, title, description, isbn, author, publisher);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");

        }
    }

    /**
     * Delete is for removing a book from the library. The primary key for this book must be supplied.
     * This an HTTP DELETE command
     * @param id primary for the book requested
     */
    public static void delete(int id) {
        Assignment1Client http = new Assignment1Client();
        try {
           http.sendDelete(url + "deleteBook/", id);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");
        }
    }

    /**
     * Quit is used to exit the console application.
     */
    public static void quit() {
        System.exit(0);
    }

    // HTTP GET request
    private void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost(String url, String title, String description, String isbn,
            String author, String publisher) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("title=" + title + "&" + "description=" + description + "&" +
        "isbn=" + isbn + "&" + "author=" + author + "&" + "publisher=" + publisher);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP PUT request
    private void sendPut(String url, int id, String title, String description, String isbn,
            String author, String publisher) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("PUT");
        con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send put request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("id=" + id  + "&" + "title=" + title  + "&" + "description=" + description  + "&" +
        "isbn=" + isbn  + "&" + "author=" + author  + "&" + "publisher=" + publisher);
        wr.flush();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP DELETE request
    private void sendDelete(String url, int id) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("DELETE");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send delete request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("id=" + id);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
