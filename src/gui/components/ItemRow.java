package gui.components;

import gui.components.iLabel;
import sql.UserItem;
import sql.RentalItem;
import utils.u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static gui.actions.actionPopup;

public class ItemRow extends JPanel {
    int id;
    static final int UserItem = 0;
    static final int RentalItem = 1;

    <E> ItemRow(E item, ActionListener listener) {
        setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //判断item的类型
        if (item instanceof UserItem) {
            id = ((UserItem) item).id;
            setUserItem((UserItem) item);
        } else if (item instanceof RentalItem) {
            id = ((RentalItem) item).id;
            setRentalItem((RentalItem) item);
        } else {
            u.err("ItemRow: 未知的item类型");
            throw new IllegalArgumentException("ItemRow: item type not supported");
        }

        //添加事件
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionPopup(ItemRow.this, listener, id, "leftClick");
            }
        });
    }

    private void setRentalItem(RentalItem item) {
        addWithGap(new iLabel(String.valueOf(item.id)));
        addWithGap(new iLabel(item.type));
        addWithGap(new iLabel(item.name));
        addWithGap(new iLabel(String.valueOf(item.numLeft)));
        addWithGap(new iLabel(String.valueOf(item.numAll)));
        addWithGap(new iLabel(String.valueOf(item.dayPrice)));
        addWithGap(new iLabel(item.addTime.toString()));
        revalidate();
        repaint();
    }


    private void setUserItem(sql.UserItem item) {
        addWithGap(new iLabel(String.valueOf(item.id)));
        addWithGap(new iLabel(item.username));
        addWithGap(new iLabel(item.registerTime.toString()));
        addWithGap(new iLabel(item.comment));
        revalidate();
        repaint();
    }

    //添加间隙
    private void addWithGap(Component comp) {
        add(comp);
        add(Box.createHorizontalStrut(50));
    }

}
