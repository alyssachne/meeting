package Controller.Sorter;

import Usecase.EventFactory;

import java.util.ArrayList;

    /**
     * An interface for sorter.
     * This interface and the classes implements it is written with some reference to
     * the strategy pattern we talked in class.
     */

public interface SorterStrategy {

    /**
     * An abstract function to sort the events that will be implemented in different classes.
     * @param tl An arraylist of the ids of the event.
     * @param ef EventFactory in use case.
     */

    void sort(ArrayList<Integer> tl, EventFactory ef);

    }
