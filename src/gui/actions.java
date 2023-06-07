package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.components.iDialog.*;

public class actions {
    public static void exitSys() {
        if (dialogConfirm(null, "警告", "您真的要退出吗？", "close")) {
            System.exit(0);
        }
    }

    public static void actionPopup(Component component, ActionListener listener, int id, String cmd) {
        listener.actionPerformed(new ActionEvent(component, id, cmd));
    }
}
