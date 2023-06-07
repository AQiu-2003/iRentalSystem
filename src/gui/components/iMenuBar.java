package gui.components;

import gui.views.AccountView;
import gui.views.RootView;
import gui.views.UserView;
import utils.u;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.actions.*;

import static gui.components.iDialog.*;

public class iMenuBar extends JMenu {
    public iMenuBar(JFrame parent, String type, ActionListener listener) {
        switch (type) {
            case "file":
                setText("文件");
                JMenuItem open = new JMenuItem("打开");
                JMenuItem save = new JMenuItem("保存");
                JMenuItem saveAs = new JMenuItem("另存为");
                JMenuItem exit = new JMenuItem("退出");
                ActionListener fileAction = e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("这个功能并没有完成...");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                        @Override
                        public boolean accept(java.io.File f) {
                            return f.getName().endsWith(".csv");
                        }

                        @Override
                        public String getDescription() {
                            return "表格文件(*.csv)";
                        }
                    });
                    fileChooser.showOpenDialog(parent);
                };
                open.addActionListener(fileAction);
                save.addActionListener(fileAction);
                saveAs.addActionListener(fileAction);
                exit.addActionListener(e -> {
                    exitSys();
                });
                add(open);
                add(save);
                add(saveAs);
                addSeparator(); // 添加分割线
                add(exit);
                break;
            case "login":
                setText("登录");
                JMenuItem adminLogin = new JMenuItem("管理员登录");
                JMenuItem userLogin = new JMenuItem("用户登录");
                ActionListener loginAction = e -> {
                    String userType = "";
                    if (e.getSource() == adminLogin) userType = "root";
                    new AccountView.LoginView(userType, listener);
                };
                adminLogin.addActionListener(loginAction);
                userLogin.addActionListener(loginAction);
                add(adminLogin);
                add(userLogin);
                break;
            case "register":
                setText("注册");
                JMenuItem userRegister = new JMenuItem("注册新用户");
                userRegister.addActionListener(e -> {
                    new AccountView.RegisterView(a -> {
                        u.log(a.getActionCommand());
                        parent.dispose();
                        new UserView();
                    });
                });
                add(userRegister);
                break;
            case "help":
                setText("帮助");
                JMenuItem help = new JMenuItem("帮助");
                help.addActionListener(e -> {
                    dialogMessage(null, "帮助", "你说的对，但是iRentalSystem是一款由AQiu自主研发的汽车租赁系统");
                });
                JMenuItem about = new JMenuItem("关于");
                about.addActionListener(e -> {
                    dialogMessage(null, "关于此系统", "你说的对，但是iRentalSystem是一款由AQiu自主研发的汽车租赁系统");
                });
                add(help);
                add(about);
                break;
        }
    }

    // 测试
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
//        menuBar.add(new iMenuBar(frame, "file"));
//        menuBar.add(new iMenuBar(frame, "login"));
//        menuBar.add(new iMenuBar(frame, "register"));
//        menuBar.add(new iMenuBar(frame, "help"));
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
