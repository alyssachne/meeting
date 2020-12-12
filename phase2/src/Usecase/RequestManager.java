package Usecase;

import Entity.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestManager implements Serializable {
    public List<Request> allRequests;

    /**
     * Initialize a RequestManager
     */
    public RequestManager() {
        allRequests = new ArrayList<>();
    }

    /**
     * Create a new request from the user and return the request Id
     * @param request : User's request
     * @param username : the username of the User
     */
    public void createRequest(String request, String username) {
        Request req = new Request(request, username, allRequests.size() + 1);
        allRequests.add(req);
    }

    /**
     * Set the request status to addressed
     * @param id: the Id of the request
     */
    public void tag(int id) {
        getRequest(id).setStatue();
    }

    /**
     * Get a user's request by the request Id. If request doesn't exist,
     * return null and print "This request does not exist."
     * @param id: the id of the Request
     * @return : the request that the id representing.
     */
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

    /**
     * Print all request from the list.
     */
    public void getAllRequests() {
        for(Request request: allRequests){
           System.out.println(request);
       }
    }
}
