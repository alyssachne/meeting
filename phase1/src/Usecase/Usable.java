package Usecase;

import Entity.*;

public interface Usable {

    boolean signUp(String username, int eventId);

    boolean cancelSpot(String username, int eventId);




}
