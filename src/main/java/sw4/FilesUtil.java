package sw4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesUtil { //not in use

    public static String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static boolean isFilenameValid(String fileName){
        File f = new File(fileName);
        return f.exists();
    }
}
