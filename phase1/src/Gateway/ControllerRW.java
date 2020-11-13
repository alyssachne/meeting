package Gateway;

import Controller.UserController;
import Entity.User;

import java.io.*;
import java.net.SocketTimeoutException;

public class ControllerRW {
    ObjectInputStream Reader;
    ObjectOutputStream Writer;
    File file;

    public ControllerRW() throws IOException {
        try {
            file = new File("./phase1/src/ControllerData.txt");
        } catch (Exception e) {
            System.out.println("File not founded!");
        }
    }

    public UserController readFile() throws IOException, ClassNotFoundException {
        Reader = new ObjectInputStream(new FileInputStream(file));
        UserController uc = null;
        if (file.length()!=0){
            uc = (UserController)Reader.readObject();
        }
        return uc;
    }

    public void writeFile(UserController uc) throws IOException {
        Writer = new ObjectOutputStream(new FileOutputStream(file));
        Writer.writeObject(uc);
        Writer.close();
    }
}
