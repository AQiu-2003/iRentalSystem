package gui.components;

import javax.swing.*;

import utils.u;

public class iLabel extends JLabel {
    public iLabel(String text) {
        setText(text);
    }

    public iLabel(String text, int width, int height) {
        setText(text);
        setSize(width, height);
    }

    public iLabel(String text, String imagePath, int width, int height) {
        if (text != null) setText(text);
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
