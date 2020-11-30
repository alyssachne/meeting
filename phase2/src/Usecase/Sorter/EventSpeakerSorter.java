package Usecase.Sorter;

import Entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventSpeakerSorter implements Sorter {


    public EventSpeakerSorter(){}


    @Override
    // Selection Sort
    public void sort(ArrayList<Event> tl) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the talk with speaker name alphabetically in the beginning of the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (tl.get(j).getSpeaker().size() == 0){
                    indexMin = j;
                }
                // If there is no speaker (i.e. the list of speakers is empty), we want to put this event at the front
                if (tl.get(indexMin).getSpeaker().size() != 0){
                    // Only proceed to compare the first item in the list of speakers' name if both of the lists are
                    // non-empty
                    String vi = tl.get(indexMin).getSpeaker().get(0);
                    String vj = tl.get(j).getSpeaker().get(0);
                    // Get the first items of the lists
                    if (vi.compareTo(vj) > 0) {
                        indexMin = j;
                    }
                }
            }

            // Swap the item at index i with the talk with speaker name alphabetically in the beginning of the list
            // between i and  list.size() - 1 inclusive.
            Event temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
// Want to sort according to speaker's name, since username may not be that well-known to other users.