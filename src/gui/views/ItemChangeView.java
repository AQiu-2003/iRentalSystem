package gui.views;

import gui.components.*;
import sql.ConnectionPool;
import sql.MySQLConfig;
import sql.RentalController;
import sql.RentalItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemChangeView {
    final int width = 500;
    RentalItem item = new RentalItem();

    ItemChangeView(ActionListener needUpdate, RentalItem source) {
        if(source != null) {
            this.item = source;
        }
        iWindow w = new iWindow("添加/更改信息", width + 100, 500);
        w.setLayout(new BorderLayout(30, 30));

        JPanel warningPanel = new JPanel(new FlowLayout());
        iLabel warningLabel = new iLabel("");
        warningLabel.setForeground(Color.RED);
        warningPanel.add(warningLabel);

        JPanel contentPanel = new JPanel(new GridLayout(4, 1));
        iField typeField = new iField("类型", width);
        typeField.setText(item.type);
        iField nameField = new iField("名称", width);
        nameField.setText(item.name);
        iField numField = new iField("数量", width);
        numField.setText(String.valueOf(item.numAll));
        iField priceField = new iField("日租价", width);
        priceField.setText(String.valueOf(item.dayPrice));
        contentPanel.add(typeField);
        contentPanel.add(nameField);
        contentPanel.add(numField);
        contentPanel.add(priceField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        iButton submit = new iButton("提交", iButton.ButtonType.PRIMARY);
        submit.addActionListener(e -> {
            item.type = typeField.getText();
            item.name = nameField.getText();
            item.numAll = Integer.parseInt(numField.getText());
            item.numLeft = item.numAll;
            item.dayPrice = Integer.parseInt(priceField.getText());

            if(item.name.equals("") || item.type.equals("") || item.numAll == 0 || item.dayPrice == 0) {
                warningLabel.setText("请填写完整信息");
            } else {
                if(source != null) {
                    RentalController.update(item, item.id);
                    iDialog.dialogMessage(w, "修改成功!", "已经成功修改了ID为" + item.id + "的项目");
                    w.dispose();
                } else  {
                    RentalController.create(item);
                    boolean choice = iDialog.dialogConfirm(w, "添加成功!", "是否继续添加？");
                    if(choice) {
                        item = new RentalItem();
                        nameField.setText("");
                        typeField.setText("");
                        numField.setText("");
                        priceField.setText("");
                    } else {
                        w.dispose();
                    }
                }
                needUpdate.actionPerformed(new ActionEvent(w, 0, "add"));
            }
        });
        iButton cancel = new iButton("取消", iButton.ButtonType.DANGER);
        cancel.addActionListener(e -> w.dispose());
        buttonPanel.add(submit);
        buttonPanel.add(cancel);


        w.add(warningPanel, BorderLayout.NORTH);
        w.add(contentPanel, BorderLayout.CENTER);
        w.add(buttonPanel, BorderLayout.SOUTH);
        w.done();
    }

    public static void main(String[] args) {
        ConnectionPool.init(new MySQLConfig());
        new ItemChangeView(e -> {
            System.out.println("add");
        }, null);

    }
}
