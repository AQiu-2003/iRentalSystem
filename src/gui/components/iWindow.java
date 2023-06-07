package gui.components;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.components.iDialog.*;

import static gui.components.iDialog.dialogConfirm;

/**
 * 新建一个窗口
 */
public class iWindow extends JFrame {
    public iWindow(String title, int width, int height) {
        setFrame(title, width, height);
        //使窗口在屏幕中间显示
        setLocationRelativeTo(null);
    }

    public iWindow(String title, int width, int height, boolean mainWindow) {
        this(title, width, height);
        if (mainWindow) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (dialogConfirm(null, "退出", "确定要退出吗？", "close"))
                        System.exit(0);
                }
            });
        }
        ;
    }


    iWindow(String title, int width, int height, JMenuBar menuBar) {
        setFrame(title, width, height);
        setJMenuBar(menuBar);
    }

    void setFrame(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 登陆前的菜单栏
     */
    public void setWelcomeMenuBar(ActionListener listener) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new iMenuBar(this, "login", listener));
        menuBar.add(new iMenuBar(this, "register", listener));
        menuBar.add(new iMenuBar(this, "help", listener));
        setJMenuBar(menuBar);
    }

    /**
     * 登陆后的菜单栏
     */
    public void setLoginMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new iMenuBar(this, "file", null));
        menuBar.add(new iMenuBar(this, "help", null));
        setJMenuBar(menuBar);
    }

    public void done() {
        setVisible(true);
    }


    // 测试
    public static void main(String[] args) {
        iWindow w = new iWindow("欢迎", 500, 500);
//        w.setWelcomeMenuBar();
        w.done();
    }
}
