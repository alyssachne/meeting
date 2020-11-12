package Gateway;

import Usecase.EventManager;

//import javafx.util.Pair;

import java.io.*;
//import java.util.ArrayList;

public class EventRW {
    BufferedReader csvReader;
    FileWriter csvWriter;
    File file;
    public EventRW() throws IOException {
        try {
            file = new File("./phase1/src/EData.csv");
        } catch (Exception e){
            System.out.println("File not founded!");
        }
    }

    public void readFile() throws IOException {
        csvReader = new BufferedReader(new FileReader(file));
        String row;
        EventManager event = new EventManager();
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
            event.createEvent(Integer.parseInt(data[0]));

        }
        csvReader.close();
    }

//    public void writeFile(Integer id, String title, Integer time, int roomId, Pair<String, String> speaker,
//                          ArrayList<String> attendees) throws IOException {
//        csvWriter = new FileWriter(file,true);
//        csvWriter.append(id+", "+title+", "+time+", "+roomId+", "+speaker.getKey()+", "+speaker.getValue()+", "+
//                attendees+"\n");
//        csvWriter.close();
//    }

    public void writeFile(int id) throws IOException {
        csvWriter = new FileWriter(file,true);
        csvWriter.append(id+"\n");
        csvWriter.close();
    }


}
