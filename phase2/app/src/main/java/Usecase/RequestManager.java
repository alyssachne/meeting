package Usecase;

import Entity.Event;
import Entity.Request;

import java.io.Serializable;
import java.util.List;

public class RequestManager implements Serializable {
    public List<Request> allRequests;

    public void createRequest(String request, String username, int id) {
        Request req = new Request(request, username, id);
        allRequests.add(req);
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
