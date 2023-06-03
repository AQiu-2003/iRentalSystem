package gui.views;

import gui.components.iField;
import gui.components.iWindow;

public class ItemAddView {
    final int width = 500;
    ItemAddView() {
        iWindow w = new iWindow("添加信息", width, 500);
        w.setLayout(null);

        iField nameField = new iField("姓名", width, null);

        w.done();
    }
}
