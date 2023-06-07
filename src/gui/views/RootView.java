package gui.views;

import gui.components.ItemViewer;
import gui.components.iButton;
import gui.components.iDialog;
import gui.components.iLabel;
import sql.ConnectionPool;
import sql.MySQLConfig;
import sql.RentalController;
import sql.RentalItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.actions.exitSys;

public class RootView extends JPanel implements ActionListener {
    ItemViewer itemViewer;
    iButton addButton = new iButton("添加", this);
    iButton deleteButton = new iButton("删除", this);
    iButton editButton = new iButton("修改", this);
    iButton queryButton = new iButton("查询", this);
    iButton exitButton = new iButton("退出", this);
    int id = 0;

    public RootView() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        iLabel titleLabel = new iLabel("管理员界面", 30);
        titleLabel.setCenter();
        titlePanel.add(titleLabel);
        iLabel idSelectedLabel = new iLabel("请选择要操作的项目");
        idSelectedLabel.setCenter();
        titlePanel.add(idSelectedLabel);
        titlePanel.setPreferredSize(new Dimension(50, 100));
        add(titlePanel, BorderLayout.NORTH);

        itemViewer = new ItemViewer(e -> {
            id = e.getID();
            idSelectedLabel.setText("您选择了ID为" + id + "的项目");
        });
        updateView();
        add(itemViewer, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(queryButton);
        buttonPanel.add(exitButton);
        buttonPanel.setPreferredSize(new Dimension(50, 100));
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (id == 0 && (e.getSource() == deleteButton || e.getSource() == editButton)) {
            iDialog.dialogError(this, "错误", "请先选择要操作的项目");
            return;
        }
        if (e.getSource() == addButton) {
            new ItemChangeView(a -> updateView(), null);
        }
        if (e.getSource() == deleteButton) {
            boolean choice = iDialog.dialogConfirm(this, "确认删除", "确认删除ID为" + id + "的项目吗？");
            if (choice) {
                RentalController.delete(id);
                updateView();
            }
        }
        if (e.getSource() == editButton) {
            new ItemChangeView(a -> updateView(), RentalController.search(id));
        }
        if (e.getSource() == queryButton) {
            String type = iDialog.dialogInput(this, "查询", "请输入要查询的项目类型");
            String name = iDialog.dialogInput(this, "查询", "请输入要查询的项目名称");
            if (type == null || name == null) {
                iDialog.dialogError(this, "错误", "查询失败");
                return;
            }
            RentalItem item = RentalController.search(type, name);
            RentalItem[] items = new RentalItem[1];
            items[0] = item;
            itemViewer.updateItem(items);
        }

        if (e.getSource() == exitButton) {
            exitSys();
        }
    }

    private void updateView() {
        RentalItem[] items = RentalController.getAll();
        itemViewer.updateItem(items);
    }

    //测试
    public static void main(String[] args) {
        ConnectionPool.init(new MySQLConfig());
        RootView rootView = new RootView();
        JFrame frame = new JFrame("RootView");
        frame.add(rootView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
