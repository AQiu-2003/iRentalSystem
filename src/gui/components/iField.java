package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class iField extends JPanel {
    public iField(String label, int width) {
        final int height = 30;
        setLayout(null);
        setSize(width, height);
        width -= 50;

        JLabel jLabel = new JLabel(label);
        jLabel.setSize((int) (width * 0.3), height);
        jLabel.setLocation((int) (width * 0.1), 0);
        jLabel.setHorizontalAlignment(JLabel.RIGHT);
//        jLabel.setBorder(BorderFactory.createLineBorder(Color.red));
        add(jLabel);

        JTextField jTextField = new JTextField();
        jTextField.setSize((int) (width * 0.6), height);
        jTextField.setLocation((int) (width * 0.4) + 10, 0);

//        setBorder(BorderFactory.createLineBorder(Color.red));

        add(jTextField);
    }

    public void setText(String text) {
        ((JTextField) getComponent(1)).setText(text);
    }

    public String getText() {
        return ((JTextField) getComponent(1)).getText();
    }
}
