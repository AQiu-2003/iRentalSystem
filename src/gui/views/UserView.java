package gui.views;

import gui.components.ItemViewer;
import gui.components.iLabel;
import sql.ConnectionPool;
import sql.MySQLConfig;
import sql.RentalController;
import sql.RentalItem;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    ItemViewer itemViewer;
    int id;

    public UserView() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        iLabel titleLabel = new iLabel("用户界面", 30);
        titleLabel.setCenter();
        titlePanel.add(titleLabel);
        iLabel idSelectedLabel = new iLabel("请选择要租赁的车辆");
        idSelectedLabel.setCenter();
        titlePanel.add(idSelectedLabel);
        titlePanel.setPreferredSize(new Dimension(50, 100));
        add(titlePanel, BorderLayout.NORTH);

        itemViewer = new ItemViewer(e -> {
            id = e.getID();
            new RentalView(id, a -> updateView());
        });
        updateView();
        add(itemViewer, BorderLayout.CENTER);
    }

    private void updateView() {
        RentalItem[] items = RentalController.getAll();
        itemViewer.updateItem(items);
    }

    public static void main(String[] args) {
        ConnectionPool.init(new MySQLConfig());
        JFrame frame = new JFrame();
        frame.setContentPane(new UserView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
