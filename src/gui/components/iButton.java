package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import utils.u;

public class iButton extends JButton {
    final int normalSize = 20;
    final int smallSize = 15;
    /**
     * 通过文本和类型创建按钮（适合5～6个汉字）
     *
     * @param text 按钮文本
     * @param type 按钮类型
     */
    public iButton(String text, ButtonType type) {
        super(text);
        setFont(new Font("Consolas", Font.PLAIN, normalSize));
        setSize(150, 50);
        switch (type) {
            case NORMAL:
                setForeground(new Color(0, 0, 0));
                break;
            case PRIMARY:
                setForeground(new Color(0, 123, 255));
                setFont(new Font("Consolas", Font.BOLD, normalSize));
                break;
            case WARNING:
                setForeground(new Color(255, 193, 7));
                setFont(new Font("Consolas", Font.BOLD, normalSize));
                break;
            case DANGER:
                setForeground(new Color(220, 53, 69));
                setFont(new Font("Consolas", Font.BOLD, normalSize));
                break;
            case SMALL:
                setForeground(new Color(0, 0, 0));
                setFont(new Font("Consolas", Font.PLAIN, smallSize));
                setSize(100, 40);
                break;
            case SMALL_PRIMARY:
                setForeground(new Color(0, 123, 255));
                setFont(new Font("Consolas", Font.BOLD, smallSize));
                setSize(100, 40);
                break;
        }
    }

    /**
     * 仅通过文本创建按钮
     *
     * @param text 按钮文本
     */
    public iButton(String text) {
        this(text, ButtonType.NORMAL);
    }

    /**
     * 通过图片路径和大小创建按钮
     *
     * @param imagePath 图片路径
     * @param size      按钮大小
     */
    public iButton(String imagePath, Dimension size) {
        ImageIcon icon = u.getImageIcon(imagePath);
        //自动缩放图片
        icon.setImage(icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
        //按钮尺寸比图片稍大
        setSize(size.width + 10, size.height + 10);
        setIcon(icon);
    }

    public void addListener(ActionListener listener) {
        addActionListener(listener);
    }

    /**
     * 按钮类型
     */
    public enum ButtonType {
        NORMAL,     //普通按钮
        PRIMARY,    //主要按钮
        WARNING,    //警告按钮
        DANGER,     //危险按钮
        SMALL,      //较小按钮
        SMALL_PRIMARY,   //较小主要按钮
    }

    /**
     * 测试所有按钮
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iButton button1 = new iButton("普通按钮", ButtonType.NORMAL);
        iButton button2 = new iButton("主要按钮", ButtonType.PRIMARY);
        iButton button3 = new iButton("警告按钮", ButtonType.WARNING);
        iButton button4 = new iButton("危险按钮", ButtonType.DANGER);
        iButton button5 = new iButton("较小按钮", ButtonType.SMALL);
        iButton button6 = new iButton("较小主要按钮", ButtonType.SMALL_PRIMARY);
        iButton button7 = new iButton("flag", new Dimension(50, 50));

        button1.setLocation(50, 50);
        button2.setLocation(50, 150);
        button3.setLocation(50, 250);
        button4.setLocation(50, 350);
        button5.setLocation(250, 50);
        button6.setLocation(250, 150);
        button7.setLocation(250, 250);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);

        frame.setVisible(true);
    }
}
