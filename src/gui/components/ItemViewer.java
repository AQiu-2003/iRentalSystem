package gui.components;

import sql.Item;
import sql.RentalCRUD;
import sql.User;
import sql.mySQLConnection;

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

    public void updateAccount(ArrayList<User> arr, Connection con, String table) {
        int max = RentalCRUD.getMaxId(con, table);
        arr.clear();
        for (int i = 1; i <= max; i++) {
            User u = new User(con, i);
            if (Objects.equals(u.get(i), "success")) {
                arr.add(u);
                u.print();
            }
        }
        if (arr.size() != 0) {
            //如果请求成功，更新数据
            this.removeAll();
            for (User u : arr) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 4));
                iLabel idLabel = new iLabel(String.valueOf(u.id));
                iLabel usernameLabel = new iLabel(u.username);
                iLabel passwordLabel = new iLabel(u.registerTime.toString());
                iLabel commentLabel = new iLabel(u.comment);
                final int strut = 30;
                panel.add(idLabel);
                panel.add(usernameLabel);
                panel.add(passwordLabel);
                panel.add(commentLabel);
                this.add(panel);
            }
        } else {
            //如果请求失败，弹出错误提示
            iDialog.dialogError(this, "错误", "数据请求失败！");
        }
    }
    public void updateItem(ArrayList<Item> arr, Connection con, String table) {
        int max = RentalCRUD.getMaxId(con, table);
        arr.clear();
        for (int i = 1; i <= max; i++) {
            Item u = new Item(con, i);
            if (Objects.equals(u.get(i), "success")) {
                arr.add(u);
                u.print();
            }
        }
        if (arr.size() != 0) {
            //如果请求成功，更新数据
            this.removeAll();
            for (Item u : arr) {
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1, 6));
                iLabel idLabel = new iLabel(String.valueOf(u.id));
                iLabel typeLabel = new iLabel(u.type);
                iLabel nameLabel = new iLabel(u.name);
                iLabel numLeftLabel = new iLabel(String.valueOf(u.numLeft));
                iLabel numAllLabel = new iLabel(String.valueOf(u.numAll));
                iLabel dayPriceLabel = new iLabel(String.valueOf(u.dayPrice));

                panel.add(idLabel);
                panel.add(typeLabel);
                panel.add(nameLabel);
                panel.add(numLeftLabel);
                panel.add(numAllLabel);
                panel.add(dayPriceLabel);
                this.add(panel);
            }
        } else {
            //如果请求失败，弹出错误提示
            iDialog.dialogError(this, "错误", "数据请求失败！");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ItemViewer");
        ItemViewer viewer = new ItemViewer(null);
//        ArrayList<User> arr = new ArrayList<>();
//        viewer.updateAccount(arr, mySQLConnection.get("rental"), "account");
        ArrayList<Item> arr = new ArrayList<>();
        viewer.updateItem(arr, mySQLConnection.get("rental"), "item");
        frame.setContentPane(viewer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
