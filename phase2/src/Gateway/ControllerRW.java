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
    public ControllerRW(){
        try {
            file = new File("./phase2/src/ControllerData.txt");
            file.createNewFile();
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
    public ControllerFacade readFile(){
        ControllerFacade uc = null;
        try{
            if (file.length()!=0){
                Reader = new ObjectInputStream(new FileInputStream(file));
                uc = (ControllerFacade)Reader.readObject();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return uc;
    }

    /**
     * Write in the file
     * @param uc: a ControllerFacade of the file
     * @throws IOException if file not found
     */
    public void writeFile(ControllerFacade uc){
        try{
            Writer = new ObjectOutputStream(new FileOutputStream(file));
            Writer.writeObject(uc);
            Writer.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
