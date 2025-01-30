package viscount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readFromStorage() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        return list;
    }

    public void clearStorage() {
        File f = new File(filePath);
        f.delete();
    }

    public void handleFileNotFound() {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public void writeToStorage(String toFile) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);
        fw.write(toFile + (toFile.isEmpty()? "" : "\n"));
        fw.close();
    }

    public void appendToStorage(String toFile) throws IOException {
        if (toFile.isEmpty()) {
            return;
        }
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, true);
        fw.write(toFile + "\n");
        fw.close();
    }
}
