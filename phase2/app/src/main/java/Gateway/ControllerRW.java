package Gateway;

import Controller.ControllerFacade;
import android.content.Context;
import android.os.Environment;
import static android.content.Context.MODE_PRIVATE;

import java.io.*;

public class ControllerRW {
    ObjectInputStream Reader;
    ObjectOutputStream Writer;
    File file;
    private Context appContext;

//    /**
//     * Find the ControllerData.txt file
//     * @throws IOException If file not found
//     */
    public ControllerRW(Context context){
        appContext = context;
        file = new File("/data/user/0/com.example.myapplication/files/ControllerData.txt");
        try{
            if (!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            System.out.println(e);
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
//            InputStream inputStream = appContext.openFileInput("ControllerData.txt");
            if (file.length()!=0){
                Reader = new ObjectInputStream(new FileInputStream(file));
                uc = (ControllerFacade)Reader.readObject();
                Reader.close();
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
