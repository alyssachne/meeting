package Gateway;

import Usecase.SpeakerAct;

import java.io.*;

public class SpeakerRW {
    BufferedReader csvReader;
    FileWriter csvWriter;
    File file;
    public SpeakerRW() throws IOException {
        try {
             file = new File("./phase1/src/SData.csv");
        } catch (Exception e){
            System.out.println("File not founded!");
        }
    }

    public void readFile() throws IOException {
        csvReader = new BufferedReader(new FileReader(file));
        String row;
        SpeakerAct act = new SpeakerAct();
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
            act.createSpeaker(data[0],data[1],data[2]);

        }
        csvReader.close();
    }

    public void writeFile(String name, String username, String password) throws IOException {
        csvWriter = new FileWriter(file,true);
        csvWriter.append(name+", "+username+", "+password+"\n");
        csvWriter.close();
    }
}
