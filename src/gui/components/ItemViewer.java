package gui.components;

import sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

public class ItemViewer extends JPanel {
    ItemViewer(ActionListener listener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    void updateItem(JPanel[] panels) {
        removeAll();
        for (JPanel panel : panels) {
            add(panel);
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        ConnectionPool.init(new MySQLConfig());

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ItemViewer viewer = new ItemViewer(null);
        RentalItem[] items = ItemController.getAll();
        JPanel[] panels = new JPanel[items.length];
        for (int i = 0; i < items.length; i++) {
            panels[i] = new ItemRow(items[i]);
        }
        viewer.updateItem(panels);
        frame.add(viewer);

        frame.setVisible(true);
    }

}
