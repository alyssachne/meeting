package Controller;

import Entity.Request;
import Usecase.RequestManager;

import java.io.Serializable;
import java.util.List;

public class RequestDealer implements Serializable {

    /**
     * Print all request from the list.
     * @param rm: request manager
     */
    public static void checkAllRequest(RequestManager rm) {
        rm.getAllRequests();
    }

    /**
     * Print all request from one User
     * @param username: the username of the User
     * @param rm: request manager
     */
    public static void checkMyRequest(String username, RequestManager rm){
        for(Request req: rm.allRequests){
            if(req.getUser().equals(username)){
                System.out.println(req);
            }
        }
    }

    /**
     * Make a new request
     * @param rm: request manager
     * @param request: the content of the request
     * @param username: the username of the User
     */
    public static void madeRequest(RequestManager rm, String request, String username) {
        rm.createRequest(request, username);
    }

    /**
     * Tag a request
     * @param id: the Id of the request
     * @param rm: request manager
     */
    public static void tagRequest(int id, RequestManager rm) {
        if(rm.getRequest(id).getStatue().equals("addressed")){
            System.out.println("This request has already been addressed");
        } else{
            rm.tag(id);
        }
    }

    /**
     * Whether there are requests in the list
     * @param rm: request manager
     * @return true if the list is not empty
     */
    public static boolean hasRequest(RequestManager rm){
        return !rm.allRequests.isEmpty();
    }

    /**
     * Whether the request is in the list or not
     * @param rm: request manager
     * @param id: the id of the request
     * @return true if the list contains the request
     */
    public static boolean containRequest(RequestManager rm, int id){
        return rm.allRequests.contains(id);
    }
}
