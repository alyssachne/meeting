package Gateway;

import Entity.Speaker;
import Entity.User;
import Usecase.SpeakerAct;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class SpeakerRW {
    ObjectInputStream Reader;
    ObjectOutputStream Writer;
    File file;

    public SpeakerRW() throws IOException {
        try {
            file = new File("./phase1/src/SData.txt");
        } catch (Exception e) {
            System.out.println("File not founded!");
        }
    }

    public ArrayList<User> readFile() throws IOException, ClassNotFoundException {
        Reader = new ObjectInputStream(new FileInputStream(file));
        ArrayList<User> userList = new ArrayList<>();
        try {
            for (; ; ) {
                User speaker = (User) Reader.readObject();
                // ...
                userList.add(speaker);
            }
        } catch (SocketTimeoutException exc) {
            // you got the timeout
        } catch (EOFException exc) {
            // end of stream
        } catch (IOException exc) {
            // some other I/O error: print it, log it, etc.
            exc.printStackTrace(); // for example
        }
        Reader.close();
        return userList;
    }

    public void writeFile(User user) throws IOException {
        Writer = new ObjectOutputStream(new FileOutputStream(file));
        Writer.writeObject(user);
        Writer.close();
    }
}
