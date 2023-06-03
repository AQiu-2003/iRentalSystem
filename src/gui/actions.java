package gui;

import static gui.components.iDialog.*;

public class actions {
    public static void exitSys() {
        if (dialogConfirm(null, "警告", "您确定要退出吗？", "close")) {
            System.exit(0);
        }
    }
}
