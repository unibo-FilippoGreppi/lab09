package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "My second Java graphical interface";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();
    /**
     * Creates a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        final JTextField textField = new JTextField(controller.getCurrentFilePath());
        final JButton browseButton = new JButton("Browse...");
        final JPanel canvas = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton saveButton = new JButton("Save");
        textField.setEditable(false);
        canvas.setLayout(new BorderLayout());
        canvas.add(textArea, BorderLayout.CENTER);
        canvas.add(saveButton, BorderLayout.SOUTH);
        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());
        browsePanel.add(textField, BorderLayout.CENTER);
        browsePanel.add(browseButton, BorderLayout.EAST);
        frame.add(browsePanel, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final var fileChooser = new JFileChooser("Choose where to save");
                fileChooser.setSelectedFile(controller.getCurrentFile());
                final var result = fileChooser.showSaveDialog(browseButton);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                    textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                if (result != JFileChooser.APPROVE_OPTION && result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(fileChooser, "Error");
                }
            }
        });
        /*
         * Handlers 
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.writeOnFile(textArea.getText());
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
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
