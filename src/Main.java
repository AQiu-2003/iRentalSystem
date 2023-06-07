import gui.components.iLabel;
import gui.components.iWindow;
import gui.views.RootView;
import gui.views.UserView;
import sql.ConnectionPool;
import sql.MySQLConfig;
import utils.u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    //在这里设置MySQL的用户名、密码、IP、端口和数据库名
    static MySQLConfig config = new MySQLConfig(
            "root",    //用户名
            "",                 //密码
            "localhost",        //IP
            3306,               //端口
            "rental"            //数据库名
    );

    public static void main(String[] args) {
        ConnectionPool.init(config);

        iWindow w = new iWindow("欢迎", 800, 800, true);
        ActionListener changePanelListener = e -> {
            String command = e.getActionCommand();
            u.log(command);
            CardLayout cardLayout = (CardLayout) w.getContentPane().getLayout();
            cardLayout.show(w.getContentPane(), command);
            w.setLoginMenuBar();
        };
        w.setWelcomeMenuBar(changePanelListener);
        w.setLayout(new CardLayout());

        //欢迎界面
        JPanel welcomePanel = new JPanel(new BorderLayout());
        iLabel welcomeImage = new iLabel(
                "欢迎使用！",
                "car-rental.png",
                200, 200,
                iLabel.VERTICAL
        );
        welcomeImage.setCenter();
        welcomePanel.add(welcomeImage, BorderLayout.CENTER);
        w.add(welcomePanel, "welcome");


        //用户租赁界面
        w.add(new UserView(), "user");
        //管理员界面
        w.add(new RootView(), "root");



        w.done();
    }
}
