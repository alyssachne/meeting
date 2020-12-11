package UI;

import Controller.ControllerFacade;
import Controller.Sorter.SorterStrategy;
import Gateway.ControllerRW;

import java.util.*;

public class AttendeeUI {

    public void AttendeeDemo(ControllerFacade uo) {
//        ControllerRW crw = new ControllerRW();
//        ControllerFacade uo = null;
//        if (crw.readFile() != null) {
//            uo = crw.readFile();
//        } else{
//            System.out.println("Read File Error");
//        }
        Scanner scanner = new Scanner(System.in);
        SortAndFilter sf = new SortAndFilter();

        boolean handle = true;
        while (handle) {
//            uo = crw.readFile();
            if (uo.checkAccess().equalsIgnoreCase("VIP")){
                System.out.print("Welcome VIP!");
            }
            System.out.println("Please enter your choice below:");
            System.out.println("1.Show my current schedule");
            System.out.println("2.Sign up an event");
//            System.out.println("3.Cancel an event reservation");
            System.out.println("3.Message system");
            System.out.println("4.Make a request");
            System.out.println("5.Exit");
            String choice = scanner.nextLine();
            if (choice.equals("1") || choice.equals("2")) {
                // filter events
                boolean filterLoop = true;
                HashMap<String, String> filterMap = new HashMap<>();
                while (filterLoop) {
                    sf.FilterHelper();
                    System.out.println("Please enter the filter and restriction you want (Enter 'exit' to skip and finish):");
                    String filter = scanner.nextLine();
                    if (filter.equals("exit")) {
                        filterLoop = false;
                    } else {
                        String restriction = scanner.nextLine();
                        filterMap.put(filter,restriction);
                    }
                }
                // sort events
                sf.SortHelper();
                String sort = scanner.nextLine();
                if (choice.equals("1")) {
                    // print out current schedule
                    System.out.println("Here is your current schedule: ");
                    if (sort.equals("exit")) {
                        uo.attendeeSchedule("Time", filterMap);
                    } else {
                        uo.attendeeSchedule(sort, filterMap);
                    }
                    System.out.println("1.Like an event");
                    System.out.println("2.Cancel an event reservation");
                    String decision = scanner.nextLine();
                    System.out.println("Please enter the eventId you would like modify");
                    String eventId = scanner.nextLine();
                    if (decision.equals("1")) {
                        uo.likeEvent(Integer.parseInt(eventId));
                    } else if (decision.equals("2")) {
                        uo.cancelSpot(Integer.parseInt(eventId));
                    } else {
                        System.out.println("This is not an valid option, please choose a number from 1 to 2.");
                    }
                } else {
                    // option 2
                    if(uo.hasEvent()) {
                        System.out.println("Here are events you can sign up: ");
                        if (sort.equals("exit")) {
                            uo.getAvailableEvent("Time", filterMap);
                        } else {
                            uo.getAvailableEvent(sort, filterMap);
                        }
                        System.out.println("Please enter the eventId you would like to sign up");
                        String eventId = scanner.nextLine();
                        uo.signUp(Integer.parseInt(eventId));
                    }else{
                        System.out.println("There is no event available.");
                    }
                }
            } else if (choice.equals("3")) {
                System.out.println("1.Send message to a specific User");
                System.out.println("2.Message Inbox");
                String option = scanner.nextLine();
                if (option.equals("1")) {
                    System.out.println("Please enter the username of the attendee");
                    String other = scanner.nextLine();
                    System.out.println("Please enter your message");
                    String message = scanner.nextLine();
                    uo.privateMessageTo(other, message);
                } else if (option.equals("2")) {
                    MessageUI mu = new MessageUI();
                    mu.MessageDemo(uo);
                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 2.");
                }
            }else if (choice.equals("4")){
                System.out.println("1.Made a new request");
                System.out.println("2.Check old requests");
                String decision = scanner.nextLine();
                if(decision.equals("1")){
                    System.out.println("Please enter your request");
                    String request = scanner.nextLine();
                    uo.madeRequest(request);
                } else if(decision.equals("2")){
                    System.out.println("Here are the requests you had made: ");
                    uo.checkMyRequest();
                } else {
                    System.out.println("This is not an valid option, please give a number from 1 to 2.");
                }
            }else if (choice.equals("5")) {
                    uo.logout();
//                    crw.writeFile(uo);
                    handle = false;
                }else{
                    System.out.println("This is not an valid option, please give a number from 1 to 6.");
                }
//            crw.writeFile(uo);
            }
        }
}
