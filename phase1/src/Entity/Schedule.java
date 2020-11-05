package Entity;

public class Schedule {

    public class LLNode {
        private Talk info;
        private LLNode link;

        public LLNode(Talk info, LLNode link) {
            this.info = info;
            this.link = link;
        }
        public Talk getInfo() { return info;}
        public LLNode getLink() { return link;}
        public void setInfo(Talk i) { info = i;}
        public void setLink(LLNode link) {
            this.link = link;
        }
    }


    public LLNode meetingAt09 = null;
    public LLNode meetingAt10 = null;
    public LLNode meetingAt11 = null;
    public LLNode meetingAt12 = null;
    public LLNode meetingAt13 = null;
    public LLNode meetingAt14 = null;
    public LLNode meetingAt15 = null;
    public LLNode meetingAt16 = null;
    public LLNode meetingAt17 = null;

    public LLNode[] talkList = new LLNode[]{meetingAt09, meetingAt10, meetingAt11, meetingAt12,
            meetingAt13, meetingAt14, meetingAt15, meetingAt16, meetingAt17};

    public void reset(){
        meetingAt09 = null;
        meetingAt10 = null;
        meetingAt11 = null;
        meetingAt12 = null;
        meetingAt13 = null;
        meetingAt14 = null;
        meetingAt15 = null;
        meetingAt16 = null;
        meetingAt17 = null;
    }

    public void insertAMeeting (Talk talk, int time){
        if (talkList[time] == null) {
            talkList[time] = new LLNode(talk, null);
        }
        LLNode temp = talkList[time];
        while (temp != null) {
            if (temp.getLink() == null) {
                LLNode newNode = new LLNode(talk, temp.getLink());
                temp.setLink(newNode);
                break;
            }
            temp = temp.getLink();
        }
        talkList[time] = temp;
    }

    public void printAllMeeting () {
        LLNode[] theTalkList = talkList;
        Talk information = null;
        for (int i = 0; i < 8; i++) {
            System.out.println("These are the talks at " + (i + 9));
            while (theTalkList[i] != null) {
                information = theTalkList[i].getInfo(); //type here is Talk
                System.out.print("Talk id:" + information.id + "Title:" + information.title + "Time:"
                        + information.time + "Duration:" + information.duration + "Room id:" +
                        information.roomId + "Speaker:" + information.speaker);
                theTalkList[i] = theTalkList[i].getLink();
            }
            System.out.println();
        }
    }

    public Talk searchByTitle (String title) {
        LLNode[] theTalkList = talkList;
        Talk information = null;
        for (int i = 0; i < 8; i++) {
            while (theTalkList[i] != null) {
                information = theTalkList[i].getInfo();
                if (information.title == title) {
                    return information;
                }
            }
        }
        System.out.println("The talk is not found.");
        return null;
    }


    public Talk searchByTimeAndSpeakerName (int t /* t -> [9,17] */, String name) {
        LLNode[] theTalkList = talkList;
        Talk information = null;
        int time = t - 9;
        while (theTalkList[time] != null) {
            information = theTalkList[time].getInfo();
            if (information.time == 1.0 * time && information.speaker == name) {
                return information;
            }
        }
        System.out.println("The talk is not found.");
        return null;
    }

    // Note: Need integer time in several places, but the input from Talk is double. I'm assuming when searching for
    // a talk by time, the user input for time would be an integer at least for now.

}

