package Controller;

import Entity.Request;
import Usecase.RequestManager;

import java.util.List;

public class RequestDealer {
    public static void checkAllRequest(RequestManager rm) {
        rm.getAllRequests();
    }

    public static void checkMyRequest(String username, RequestManager rm){
        System.out.println("Here are your requests: ");
        for(Request req: rm.allRequests){
            if(req.getUser().equals(username)){
                System.out.println(req);
            }
        }
    }

    public static void madeRequest(RequestManager rm, String request, String username) {
        rm.createRequest(request, username);
    }

    public static void tagRequest(int id, RequestManager rm) {
        if(rm.getRequest(id).getStatue().equals("addressed")){
            System.out.println("This request has already been addressed");
        } else{
            rm.tag(id);
        }
    }
}
