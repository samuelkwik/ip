package viscount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for handling file-based storage operations.
 * It provides functionalities to read data from, write data to, and manage a storage file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from a storage file and returns it as a list of strings.
     * Each string in the list represents a line of content from the file.
     *
     * @return An ArrayList containing lines of content from the storage file.
     * @throws FileNotFoundException If the specified file does not exist.
     */
    public ArrayList<String> readFromStorage() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        return list;
    }

    /**
     * Deletes the storage file located at the specified file path.
     * If the file exists, it will be permanently removed from the filesystem.
     * This method does not throw an exception if the file does not exist.
     */
    public void clearStorage() {
        File f = new File(filePath);
        f.delete();
    }

    /**
     * Handles a scenario where a file is not found at the specified file path.
     * This method ensures that the necessary directory structure for the file is created,
     * facilitating subsequent file creation or other file-related operations.
     */
    public void handleFileNotFound() {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    /**
     * Writes the specified string content to the storage file. Appends a newline character
     * to the content if it is not empty.
     *
     * @param toFile The content to be written to the storage file.
     *               If the string is empty, no newline character is appended.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToStorage(String toFile) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);
        fw.write(toFile + (toFile.isEmpty() ? "" : "\n"));
        fw.close();
    }
}
