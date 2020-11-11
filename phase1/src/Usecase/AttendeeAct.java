package Usecase;

import Entity.Attendee;

public class AttendeeAct {
    public void createAttendee(String name, String username, String password) {
        Attendee attendee = new Attendee(name, username, password);
    }
}
