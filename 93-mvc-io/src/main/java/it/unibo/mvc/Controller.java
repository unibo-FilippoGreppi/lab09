package it.unibo.mvc;

import java.util.List;
/**
 * Interface of a simple controller.
 */
public interface Controller {
    /**
     * Set the next string to print.
     * @param nextString next string to be printed
     * @throws IllegalArgumentException Null values are not acceptable
     */
    void setNextStringToPrint(String nextString);

    /**
     * @return next string to print
     */
    String getNextStringToPrint();

    /**
     * @return history of the printed strings
     */
    List<String> getPrintHistory();

    /**
     * Print the current string.
     * @throws IllegalStateException if the current string is unset
     */
    void printCurrentString();
}
