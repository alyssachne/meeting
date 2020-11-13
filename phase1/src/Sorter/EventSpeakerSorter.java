package Sorter;

import Entity.Event;

import java.util.ArrayList;

public class EventSpeakerSorter implements Sorter {


    public EventSpeakerSorter(){}


    @Override
    // Selection Sort
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the talk with title alphabetically in the beginning of the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                String vi = tl.get(indexMin).speaker;
                String vj = tl.get(j).speaker;
                // Want to sort according to speaker's name, since username may not be that well-known to other users.
                if (vi.compareTo(vj) > 0) {
                    indexMin = j;
                }
            }

            // Swap the item at index i with the talk with title alphabetically in the beginning of the list
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
