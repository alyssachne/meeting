package UI;

import java.util.HashMap;
import java.util.Scanner;

public class SortAndFilter {
    public SortAndFilter (){};

    public void SortHelper(){
        System.out.println("Sorting option: ");
        System.out.println("1. Sort by eventId. (Input 'EventId')");
        System.out.println("2. Sort by roomId. (Input 'RoomId')");
        System.out.println("3. Sort by speaker's username. (Input 'Speaker')");
        System.out.println("4. Sort by starting time of event. (Input 'Time')");
        System.out.println("5. Sort by title of event. (Input 'Title')");
        System.out.println("Please choose one of the options. For example, enter eventId, if you want to choose the " +
                "first option. (Enter 'exit' to skip and finish.)");
    }

    public HashMap<String, String> FilterHelper(Scanner scanner){
        // filter events
        boolean filterLoop = true;
        HashMap<String, String> filterMap = new HashMap<>();
        while (filterLoop) {
            System.out.println("Filter option: ");
            System.out.println("1. Filter by speaker of event. (Input 'Speaker' and the username of the speaker)");
            System.out.println("2. Filter by starting time of event. (Input 'time' and starting time of the event: an integer)");
            System.out.println("3. Filter by date. (Input 'date' and the date in dd/mm/yyyy)");
            System.out.println("Please choose options you want one at a time. (Enter filter type first then press Enter " +
                    "then type in the restriction for the selected filter.");
            System.out.println("Please enter the filter and restriction you want (Enter 'exit' to skip and finish):");
            String filter = scanner.nextLine();
            if (filter.equals("exit")) {
                filterLoop = false;
            } else {
                String restriction = scanner.nextLine();
                filterMap.put(filter, restriction);
            }
        }
        return filterMap;
    }
}
