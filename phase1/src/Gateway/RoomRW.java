package Gateway;

import Usecase.RoomManager;

import java.io.*;

public class RoomRW {
    BufferedReader csvReader;
    FileWriter csvWriter;
    File file;
    public RoomRW() throws IOException {
        try {
            file = new File("./phase1/src/RData.csv");
        } catch (Exception e){
            System.out.println("File not founded!");
        }
    }

    public void readFile() throws IOException {
        csvReader = new BufferedReader(new FileReader(file));
        String row;
        RoomManager room = new RoomManager();
        csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
            room.createRoom(Integer.parseInt(data[0]), Integer.parseInt(data[1]));

        }
        csvReader.close();
    }

    public void writeFile(int id, int capacity) throws IOException {
        csvWriter = new FileWriter(file,true);
        csvWriter.append(id+","+capacity+","+"\n");
        csvWriter.close();
    }
}
