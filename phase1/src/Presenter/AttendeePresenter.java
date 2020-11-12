package Presenter;

public class AttendeePresenter {
    public void mainMenu(){
        System.out.println("Please enter your choice below:");
        System.out.println("1.See the schedule of events you can sign up");
        System.out.println("2.See the schedule of events you signed up");
        System.out.println("3.Send messages");
        System.out.println("4.Check mailbox");
    }

    public void eventSignUpMenu(){
        System.out.println("Please enter the event name you want to sign up");
    }

    public void eventCancelMenu(){
        System.out.println("Please enter the event name you want to cancel");
    }

    public void sendMessagesMenu(){
        System.out.println("Please enter the receiver name");
        System.out.println("Please enter message");
    }

    public void checkMailbox(){
        System.out.println("Please enter the sender name");
    }
}
