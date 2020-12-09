package Controller.Sorter;

import Entity.Event;
import Usecase.EventFactory;

import java.util.ArrayList;

public interface SorterStrategy {

    void sort(ArrayList<Integer> tl, EventFactory ef);

    // This interface and the classes implements it is written with some reference to
    // the strategy pattern we talked in class.

}
