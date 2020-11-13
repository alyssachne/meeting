package Presenter;

public class SpeakerPresenter {
    public void mainMenu(){
        System.out.println("Please enter your choice below:");
        System.out.println("1.Set title of a speech");
        System.out.println("2.Send a message");
    }

    public void setTitle(){
        System.out.println("Please enter the title of the speech:");
        System.out.println("Press Enter button after your entry");
    }

    public void receiver1(){
        System.out.println("Please select the group of receivers you want to message to");
        System.out.println("I want to send message to all the attendees from my talk(s)");
        System.out.println("I want to specify receivers myself.");
    }

    public void receiver2(){
        System.out.println("Please input the talk id of the talk you want to send group message to.");
    }

    public void receiver3(){
        System.out.println("Please put below the user name(s) of the users you want to send meaasages to, if you want " +
                "to send messages to multiple users, please separate the usernames with comma");
    }
}