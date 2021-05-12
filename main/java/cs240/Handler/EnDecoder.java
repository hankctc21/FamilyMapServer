package cs240.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Jeong Suk(Jerry) Han on 2017-11-03.
 */

public class EnDecoder {



    public String fileToString(String filename) throws IOException {
            return new String(Files.readAllBytes(Paths.get(filename)));
    }

    public void stringToFile(String string, String filename) {
        try {
            Files.write(Paths.get(filename), string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
