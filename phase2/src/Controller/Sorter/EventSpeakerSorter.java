package Controller.Sorter;

import Usecase.EventFactory;

import java.util.ArrayList;

    /**
     * The class to implement sorter by speaker.
     */

public class EventSpeakerSorter implements SorterStrategy {


    public EventSpeakerSorter(){}

    /**
     * A function to implement sort by sorting the events according to their speakers' names alphabetically.
     * @param tl An arraylist of the ids of the event.
     * @param ef The EventFactory object in use case.
     */

    @Override
    // Selection Sort
    public void sort(ArrayList<Integer> tl, EventFactory ef) {
        for (int i = 0; i < tl.size() - 1; i++) {

            // Find the index of the talk with speaker name alphabetically in the beginning of the list between
            // indices i and tl.size() - 1 inclusive.
            int indexMin = i;
            for (int j = i + 1; j < tl.size(); j++) {
                if (ef.getEvent(tl.get(j)).getSpeaker().size() == 0){
                    indexMin = j;
                }
                // If there is no speaker (i.e. the list of speakers is empty), we want to put this event at the front
                if (ef.getEvent(tl.get(indexMin)).getSpeaker().size() != 0){
                    // Only proceed to compare the first item in the list of speakers' name if both of the lists are
                    // non-empty
                    String vi = ef.getEvent(tl.get(indexMin)).getSpeaker().get(0);
                    String vj = ef.getEvent(tl.get(j)).getSpeaker().get(0);
                    // Get the first items of the lists
                    if (vi.compareTo(vj) > 0) {
                        indexMin = j;
                    }
                }
            }

            // Swap the item at index i with the talk with speaker name alphabetically in the beginning of the list
            // between i and list.size() - 1 inclusive.
            int temp = tl.get(i);
            tl.set(i, tl.get(indexMin));
            tl.set(indexMin, temp);
        }
    }
}
// Want to sort according to speaker's name, since username may not be that well-known to other users.