package it.unibo.mvc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private String currentString;
    private final List<String> printHistory = new LinkedList<>();

    @Override
    public void setNextStringToPrint(final String nextString) {
        this.currentString = Objects.requireNonNull(nextString, "String to be printed can not be" + nextString);
    }

    @Override
    public String getNextStringToPrint() {
        return this.currentString;
    }

    @Override
    public List<String> getPrintHistory() {
        return new ArrayList<>(this.printHistory);
    }

    @Override
    public void printCurrentString() {
        if (this.currentString == null) {
            throw new IllegalArgumentException("String to be printed is unset");
        }
        printHistory.add(this.currentString);
        System.out.println(this.currentString); // NOPMD: allowed as this is just an exercise
    }
}
