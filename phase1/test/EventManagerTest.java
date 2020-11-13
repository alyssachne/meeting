import Usecase.EventManager;
import Entity.Event;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class EventManagerTest {

    // Event Manager constructor
    @Test(timeout = 50)
    public void testEventManager(){
        EventManager em = new EventManager();
    }

    // createEvent same time different rooms
    @Test
    public void testCreateEvent1(){
        EventManager em1 = new EventManager();
        em1.createEvent(1, "aaa",9,1);
        em1.createEvent(2,"bbb",9,2);
        em1.createEvent(3,"ccc",9,3);

        assertEquals(new Event(1,"aaa",9,1),em1.getEvent(1));
        assertEquals(new Event(2,"bbb",9,2),em1.getEvent(2));
        assertEquals(new Event(3,"ccc",9,3),em1.getEvent(3));
    }

    // createEvent same room different times
    @Test
    public void testCreateEvent2(){
        EventManager em2 = new EventManager();
        em2.createEvent(1, "aaa",9,1);
        em2.createEvent(2,"bbb",10,1);
        em2.createEvent(3,"ccc",11,1);

        assertEquals(new Event(1,"aaa",9,1),em2.getEvent(1));
        assertEquals(new Event(2,"bbb",10,1),em2.getEvent(2));
        assertEquals(new Event(3,"ccc",11,1),em2.getEvent(3));
    }

    // createEvent event time out of range
    @Test
    public void testCreateEvent3(){
        EventManager em3 = new EventManager();
        em3.createEvent(1, "aaa",8,1);
        em3.createEvent(2,"bbb",10,2);
        em3.createEvent(3,"ccc",17,3);

        assertNull(em3.getEvent(1));
        assertEquals(new Event(2,"bbb",10,2),em3.getEvent(2));
        assertNull(em3.getEvent(3));
    }

    // createEvent event room and time conflict
    @Test
    public void testCreateEvent4(){
        EventManager em4 = new EventManager();
        em4.createEvent(1, "aaa",9,2);
        em4.createEvent(2,"bbb",10,2);
        em4.createEvent(3,"ccc",10,2);

        assertEquals(new Event(1,"aaa",9,2),em4.getEvent(1));
        assertEquals(new Event(2,"bbb",10,2),em4.getEvent(2));
        assertNull(em4.getEvent(3));
    }

    // getEvent can be tested together with create Event
    // not sure what setTime means

}
