package gui.components;

import gui.components.iLabel;
import sql.UserItem;
import sql.RentalItem;
import utils.u;

import javax.swing.*;

public class ItemRow extends JPanel {
    static final int UserItem = 0;
    static final int RentalItem = 1;

    <E> ItemRow(E item) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //判断item的类型
        if (item instanceof UserItem) {
            setUserItem((UserItem) item);
        } else if (item instanceof RentalItem) {
            setRentalItem((RentalItem) item);
        } else {
            u.err("ItemRow: 未知的item类型");
            throw new IllegalArgumentException("ItemRow: item type not supported");
        }
    }

    private void setRentalItem(RentalItem item) {
        add(new iLabel(String.valueOf(item.id)));
        add(new iLabel(item.type));
        add(new iLabel(item.name));
        add(new iLabel(String.valueOf(item.numLeft)));
        add(new iLabel(String.valueOf(item.numAll)));
        add(new iLabel(String.valueOf(item.dayPrice)));
        add(new iLabel(item.addTime.toString()));
        revalidate();
        repaint();
    }


    private void setUserItem(sql.UserItem item) {
        add(new iLabel(String.valueOf(item.id)));
        add(new iLabel(item.username));
        add(new iLabel(item.registerTime.toString()));
        add(new iLabel(item.comment));
        revalidate();
        repaint();
    }

}
