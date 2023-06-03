package gui.components;

import javax.swing.*;

/**
 * 新建一个窗口
 */
public class iWindow extends JFrame {
    public iWindow(String title, int width, int height) {
        setFrame(title, width, height);
    }
    iWindow(String title, int width, int height, JMenuBar menuBar) {
        setFrame(title, width, height);
        setJMenuBar(menuBar);
    }

    void setFrame(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 登陆前的菜单栏
     */
    public void setWelcomeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new iMenuBar("file"));
        menuBar.add(new iMenuBar("login"));
        menuBar.add(new iMenuBar("register"));
        menuBar.add(new iMenuBar("about"));
        setJMenuBar(menuBar);
    }

    public void visible() {
        setVisible(true);
    }


    public static void main(String[] args) {
        new iWindow("Login", 500, 500);
    }
}
