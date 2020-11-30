package Usecase.Sorter;

import Entity.Event;

import java.util.ArrayList;

public class EventRoomSorter implements Sorter{

    public EventRoomSorter(){}


    @Override
    // Selection Sort
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the item with the smallest roomId in the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (tl.get(indexMin).getRoom() > tl.get(j).getRoom()) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the item with the smallest roomId
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
