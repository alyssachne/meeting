package Gateway;

import Controller.ControllerFacade;

import java.io.*;

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

    public ControllerFacade readFile() throws IOException, ClassNotFoundException {
        ControllerFacade uc = null;
        if (file.length()!=0){
            Reader = new ObjectInputStream(new FileInputStream(file));
            uc = (ControllerFacade)Reader.readObject();
        }
        return uc;
    }

    public void writeFile(ControllerFacade uc) throws IOException {
        Writer = new ObjectOutputStream(new FileOutputStream(file));
        Writer.writeObject(uc);
        Writer.close();
    }
}
