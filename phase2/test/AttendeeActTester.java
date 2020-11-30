import Usecase.AttendeeAct;
import Entity.Event;
import Entity.Attendee;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class AttendeeActTester {
    // Test AttendeeAct constructor
    @Test(timeout = 50)
    public void testAttendeeAct(){
        AttendeeAct Aa = new AttendeeAct();
    }

    // Test createAttendee
    @Test
    public void testCreateAttendee(){
        AttendeeAct Aa1 = new AttendeeAct();
        Aa1.createAttendee("Amy", "amy01", "niceDay1");

        assertSame("amy01", Aa1.getAttendee("amy01").getUsername());
    }

    // Test login
    @Test
    public void testLogin(){
        AttendeeAct Aa2 = new AttendeeAct();
        Aa2.createAttendee("Amy", "amy02", "niceDay2");
        // password matches
        assertFalse(Aa2.login("amy02", "niceDay2"));
        // password does not match
        assertTrue(Aa2.login("amy02","nicEDay2"));
    }

    // Test getEvents all events are successfully added
    @Test
    public void testGetEvents1(){
        AttendeeAct Aa3 = new AttendeeAct();
        Aa3.createAttendee("Amy", "amy03", "niceDay3");

        Event e1 = new Event(1,"aaa",9,1, "a1");
        Event e2 = new Event(2,"bbb",10,1, "a2");
        Event e3 = new Event(5,"ababa",15,2, "a3");

        Aa3.getAttendee("amy03").signUp(1);
        Aa3.getAttendee("amy03").signUp(2);
        Aa3.getAttendee("amy03").signUp(5);

        ArrayList<Integer> l1 = Aa3.getEvents("amy03");

        assertTrue(l1.contains(1));
        assertTrue(l1.contains(2));
        assertTrue(l1.contains(5));

        assertFalse(l1.contains(3));
    }

    // Test getEvents some events are not added for time conflict of the attendee
    @Test
    public void testGetEvents2(){
        AttendeeAct Aa4 = new AttendeeAct();
        Aa4.createAttendee("Amy", "amy04", "niceDay4");

        Event e1 = new Event(1,"aaa",9,1, "a1");
        Event e2 = new Event(2,"bbb",10,1, "a2");
        Event e3 = new Event(5,"ababa",10,2, "a3");

        Aa4.getAttendee("amy04").signUp(1);
        Aa4.getAttendee("amy04").signUp(2);
        Aa4.getAttendee("amy04").signUp(5);

        ArrayList<Integer> l1 = Aa4.getEvents("amy04");

        assertTrue(l1.contains(1));
        assertTrue(l1.contains(2));
        assertFalse(l1.contains(5));
        // Event with id 5 is not added since the attendee already has an event at 10
        assertFalse(l1.contains(3));
    }
}
