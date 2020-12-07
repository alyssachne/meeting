package Controller.Sorter;

import Entity.Event;

import java.util.ArrayList;

public class EventTimeSorter implements Sorter{

    public EventTimeSorter(){}

    @Override
    // Selection Sort
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the earliest time in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (tl.get(indexMin).getTime() > tl.get(j).getTime()) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the earliest time
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
