package gui.components;

import javax.swing.*;

import static gui.actions.*;

public class iMenuBar extends JMenu {
    public iMenuBar(String type) {
        switch (type) {
            case "file":
                setText("文件");
                JMenuItem open = new JMenuItem("打开");
                JMenuItem save = new JMenuItem("保存");
                JMenuItem saveAs = new JMenuItem("另存为");
                JMenuItem exit = new JMenuItem("退出");
                exit.addActionListener(e -> {exitSys();});
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
                add(adminLogin);
                add(userLogin);
                break;
            case "register":
                setText("注册");
                JMenuItem userRegister = new JMenuItem("注册新用户");
                add(userRegister);
                break;
            case "about":
                setText("关于");
                addActionListener(e -> {

                });
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
        menuBar.add(new iMenuBar("file"));
        menuBar.add(new iMenuBar("login"));
        menuBar.add(new iMenuBar("register"));
        menuBar.add(new iMenuBar("about"));
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
