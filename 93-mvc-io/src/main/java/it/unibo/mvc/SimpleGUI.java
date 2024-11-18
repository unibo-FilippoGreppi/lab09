package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private static final String NEWLINE = System.getProperty("line.separator");
    private final Controller controller;
    /**
     * Creates a SimpleGUI.
     * @param controller
     */
    @SuppressFBWarnings(
        value = { "EI_EXPOSE_REP2" },
        justification = "The controller is designed to be manipulated this way."
    )
    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JPanel canvas = new JPanel();
        final JButton printButton = new JButton("Print");
        final JButton historyButton = new JButton("Show History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);
        canvas.add(printButton, BorderLayout.EAST);
        canvas.add(historyButton, BorderLayout.WEST);
        frame.add(canvas, BorderLayout.SOUTH);
        /**
         * Handlers
         */
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.controller.setNextStringToPrint(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                for (final var str : SimpleGUI.this.controller.getPrintHistory()) {
                    text.append(str).append(NEWLINE);
                }
                textArea.setText(text.toString());
            }
        });
    }
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
        */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }
    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
       new SimpleGUI(new SimpleController()).display();
    }
}
