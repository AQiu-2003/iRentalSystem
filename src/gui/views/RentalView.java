package gui.views;

import gui.components.iDialog;
import sql.RentalController;
import sql.RentalItem;

import java.awt.event.ActionListener;

public class RentalView {
    RentalView(int id, ActionListener needUpdate) {
        RentalItem item = RentalController.search(id);
        String input = iDialog.dialogInput(null, "租借", "请输入租借天数");
        if(input == null) return;
        int days = Integer.parseInt(input);
        if (days > 0) {
            assert item != null;
            if (item.numLeft == 0) {
                iDialog.dialogMessage(null, "租借失败", "该车辆已经全部租出");
                return;
            }
            int money = item.dayPrice * days;
            boolean choice = iDialog.dialogConfirm(null, "租借", "您需要支付" + money + "元，是否继续？", "租借");
            if (!choice) return;
            iDialog.dialogMessage(null, "租借成功", "您已经成功租借了" + item.name + "，租借天数为" + days + "天");
            item.numLeft--;
            RentalController.update(item, item.id);
            needUpdate.actionPerformed(null);
        }
    }
}
