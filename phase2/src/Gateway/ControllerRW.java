package Gateway;

import Controller.ControllerFacade;

import java.io.*;

public class ControllerRW {
    ObjectInputStream Reader;
    ObjectOutputStream Writer;
    File file;

    /**
     * Find the ControllerData.txt file
     * @throws IOException If file not found
     */
    public ControllerRW() throws IOException {
        try {
            file = new File("./phase1/src/ControllerData.txt");
        } catch (Exception e) {
            System.out.println("File not founded!");
        }
    }

    /**
     * Read the file
     * @return the objects in the file
     * @throws IOException If file not found
     * @throws ClassNotFoundException If Class not found
     */
    public ControllerFacade readFile() throws IOException, ClassNotFoundException {
        ControllerFacade uc = null;
        if (file.length()!=0){
            Reader = new ObjectInputStream(new FileInputStream(file));
            uc = (ControllerFacade)Reader.readObject();
        }
        return uc;
    }

    /**
     * Write in the file
     * @param uc: a ControllerFacade of the file
     * @throws IOException if file not found
     */
    public void writeFile(ControllerFacade uc) throws IOException {
        Writer = new ObjectOutputStream(new FileOutputStream(file));
        Writer.writeObject(uc);
        Writer.close();
    }
}
