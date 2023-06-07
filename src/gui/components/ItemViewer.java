package gui.components;

import sql.*;
import utils.u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ItemViewer extends JPanel {
    ActionListener listener;

    public ItemViewer(ActionListener listener) {
        this.listener = listener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public <E> void updateItem(E[] items) {
        removeAll();
        for (E item : items) add(new ItemRow(item, listener));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        ConnectionPool.init(new MySQLConfig());

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ItemViewer viewer = new ItemViewer(e -> {
            u.log("ItemViewer: left click on item " + e.getID());
        });
        RentalItem[] items = RentalController.getAll();
        JPanel[] panels = new JPanel[items.length];
        viewer.updateItem(items);
        frame.add(viewer);

        frame.setVisible(true);
    }

}
