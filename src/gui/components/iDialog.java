package gui.components;

import utils.u;

import javax.swing.*;
import java.awt.*;

public class iDialog {
    public static void dialogMessage(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent, message, title,
                JOptionPane.PLAIN_MESSAGE,
                u.getImageIcon("comment", 50, 50)
        );
    }

    public static void dialogError(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent, message, title,
                JOptionPane.ERROR_MESSAGE,
                u.getImageIcon("stop", 50, 50)
        );
    }

    public static void dialogWarning(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent, message, title,
                JOptionPane.WARNING_MESSAGE,
                u.getImageIcon("warning", 50, 50)
        );
    }

    public static boolean dialogConfirm(Component parent, String title, String message) {
        return JOptionPane.showConfirmDialog(
                parent, message, title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                u.getImageIcon("select", 50, 50)
        ) == JOptionPane.YES_OPTION;
    }

    public static boolean dialogConfirm(Component parent, String title, String message, String imagePath) {
        return JOptionPane.showConfirmDialog(
                parent, message, title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                u.getImageIcon(imagePath, 50, 50)
        ) == JOptionPane.YES_OPTION;
    }

    public static String dialogInput(Component parent, String title, String message) {
        return JOptionPane.showInputDialog(
                parent, message, title,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public static int dialogChoice(Component parent, String title, String message, String[] options) {
        return JOptionPane.showOptionDialog(
                parent, message, title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                u.getImageIcon("adjust", 50, 50),
                options,
                options[0]
        );
    }

    // Test
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        dialogMessage(frame, "Message", "This is a message.");
        dialogError(frame, "Error", "This is an error.");
        dialogWarning(frame, "Warning", "This is a warning.");
        System.out.println(dialogConfirm(frame, "Confirm", "Do you want to continue?"));
        System.out.println(dialogInput(frame, "Input", "Please input your name:"));
        System.out.println(dialogChoice(frame, "Choice", "Please choose one:", new String[]{"A", "B", "C", "D"}));
    }

}
