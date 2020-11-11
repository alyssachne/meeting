import Gateway.SpeakerRW;

import java.io.IOException;

public class RWTester {
    public static void main(String[] args) throws IOException {
        SpeakerRW rw = new SpeakerRW();
//        rw.readFile();
        rw.writeFile("sp1","sp1","sp1");
        rw.writeFile("sp1","sp1","sp1");
        rw.writeFile("sp1","sp1","sp1");
    }
}
