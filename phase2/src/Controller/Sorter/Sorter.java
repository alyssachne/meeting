package Controller.Sorter;

import Entity.Event;

import java.util.ArrayList;

public interface Sorter {

    void sort(ArrayList<Event> tl);

    // This interface and the classes implements it is written with some reference to
    // the strategy pattern we talked in class.

}
