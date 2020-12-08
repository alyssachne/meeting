package Controller;

import Entity.Request;
import Usecase.RequestManager;

import java.util.List;

public class RequestDealer {
    public static List<Request> checkAllRequest(RequestManager rm) {
        return rm.getAllRequests();
    }

    public static void madeRequest(RequestManager rm, String request, String username) {
        rm.createRequest(request, username);
    }

    public static void tagRequest(int id, RequestManager rm) {
        rm.tag(id);
    }
}
