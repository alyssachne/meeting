package Entity;

import java.io.Serializable;
import java.util.Observable;

public class Request extends Observable implements Serializable {
    private String statue;
    private String request;
    private String username;
    private int id;

    public Request(String request, String username, int id) {
        this.request = request;
        this.username = username;
        this.id = id;
        this.statue = "pending";
    }

    public String getUser() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue() {
        statue = "addressed";
        notifyObservers("This request " + request + " made by" + username + " is addressed.");
    }

    @Override
    public String toString()  {
        System.out.print("This request " + id + " is made by " + username + " :");
        System.out.println(request);
        System.out.println("It is " + statue);
        return null;
    }

}
