package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile = new File(System.getProperty("user.home")
    + File.separator
    + "output.txt");

    /**
     * Set the new file as current file.
     * @param newFile file to be set as current
     */
    public void setCurrentFile(final File newFile) {
        this.currentFile = newFile;
    }
    /**
     * @return current file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }
    /** 
     * @return current file path as a string.
     */
    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }
    /**
     * Write @param inputString on the current file.
     * @param inputString text to be saved
     * @throws IOException
     */
    public void writeOnFile(final String inputString) {
        try (PrintStream ps = new PrintStream(getCurrentFilePath(), StandardCharsets.UTF_8)) {
            ps.print(inputString);
        } catch (IOException e) {
            e.printStackTrace(); // NOPMD: allowed as this is just an exercise
        }
    }
}
