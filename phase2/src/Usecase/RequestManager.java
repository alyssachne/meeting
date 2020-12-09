package Usecase;

import Entity.Event;
import Entity.Request;
import com.sun.java.accessibility.util.AccessibilityListenerList;
import javafx.beans.InvalidationListener;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

public class RequestManager implements Serializable {
    public List<Request> allRequests;

    public int createRequest(String request, String username) {
        Request req = new Request(request, username, allRequests.size() + 1);
        allRequests.add(req);
        return req.getId();
    }

    public void tag(int id) {
        getRequest(id).setStatue();
    }

    public Request getRequest(int id) {
        try {
            for (Request req: allRequests) {
                if(req.getId() == id) {
                    return req;
                }
            }
        }
        catch (Exception e) {
            System.out.println("This request does not exist.");
        }
        return null;
    }

    public List<Request> getAllRequests() {
       return allRequests;
    }
}
