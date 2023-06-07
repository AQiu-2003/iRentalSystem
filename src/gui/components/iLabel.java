package gui.components;

import javax.swing.*;
import java.awt.*;

import utils.u;

public class iLabel extends JLabel {
    public iLabel(String text) {
        this(text, 20);
    }
    public iLabel(String text, int fontSize) {
        setText(text);
        setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
//        setHorizontalAlignment(JLabel.CENTER);
    }

    public iLabel(String text, int width, int height) {
        this(text);
        setSize(width, height);
    }

    public iLabel(String text, String imagePath, int width, int height) {
        this(text);
        setSize(width, height);
        setIcon(u.getImageIcon(imagePath, width, height));
    }
    public iLabel(String text, String imagePath, int width, int height, int align) {
        this(text, imagePath, width, height);
        switch (align) {
            case HORIZONTAL:
                break;
            case VERTICAL:
                setHorizontalTextPosition(JLabel.CENTER);
                setVerticalTextPosition(JLabel.BOTTOM);
                break;
        }
    }

    public void setCenter() {
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    public void setLeading() {
        setHorizontalAlignment(JLabel.LEADING);
    }

    public void setTrailing() {
        setHorizontalAlignment(JLabel.TRAILING);
    }

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
}
