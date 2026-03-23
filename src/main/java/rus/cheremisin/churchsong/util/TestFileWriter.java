package rus.cheremisin.churchsong.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestFileWriter {

    public static void writeResponse(String responseString) {
        try {
            File newFile = new File("TestResponse.txt");
            newFile.createNewFile();
            Files.write(newFile.toPath(), responseString.getBytes());
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
