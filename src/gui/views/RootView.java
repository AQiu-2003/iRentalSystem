package gui.views;

import gui.components.iButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RootView extends JPanel implements ActionListener {
    iButton addButton = new iButton("添加");
    iButton deleteButton = new iButton("删除");
    iButton updateButton = new iButton("修改");
    iButton queryButton = new iButton("查询");
    iButton exitButton = new iButton("退出");

    public RootView() {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(queryButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //测试
    public static void main(String[] args) {
        RootView rootView = new RootView();
        JFrame frame = new JFrame("RootView");
        frame.add(rootView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
