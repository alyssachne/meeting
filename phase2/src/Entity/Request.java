package Entity;

import java.io.Serializable;
import java.util.Observable;

public class Request extends Observable implements Serializable {
    private String statue;
    private String request;
    private String username;
    private int id;

    /**
     * Initialize a new request
     * @param request
     * @param username
     * @param id
     */
    public Request(String request, String username, int id) {
        this.request = request;
        this.username = username;
        this.id = id;
        this.statue = "pending";
    }

    /**
     * Get username of the User of the request
     * @return username's string
     */
    public String getUser() {
        return username;
    }

    /**
     * Get request's Id
     * @return the integer of the Id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the statue of request
     * @return a string that show the statue
     */
    public String getStatue() {
        return statue;
    }

    /**
     * Set request statue
     */
    public void setStatue() {
        statue = "addressed";
        notifyObservers("This request " + request + " made by" + username + " is addressed.");
    }

    /**
     * Print a request
     * @return the string of the request
     */
    public String toString()  {
        return "This request "+ id + " is made by " + username + " :" + "\n" + request + "\n It is" + statue;
    }

}
