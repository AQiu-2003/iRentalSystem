package gui.views;

import gui.components.iButton;
import gui.components.iField;
import gui.components.iLabel;
import gui.components.iWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.u;

public class AccountView {
    public static class LoginView {
        final int width = 300;
        iWindow w = new iWindow("用户登录", width, 300);

        public LoginView(String username, ActionListener listener) {
            iButton loginButton = new iButton("登录", iButton.ButtonType.SMALL);
            iField usernameField = new iField("用户名", width, null);
            iField passwordField = new iField("密码", width, null);
            iLabel errorMessage = new iLabel("");

            w.setLayout(null);
            loginButton.setBounds(100, 168, 100, 30);
            errorMessage.setBounds(0, 200, width, 50);
            errorMessage.setForeground(Color.RED);

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(2, 1));
            fieldPanel.setBounds(0, 50, width, 100);
            usernameField.setText(username);
            fieldPanel.add(usernameField);
            fieldPanel.add(passwordField);

            loginButton.addListener(e -> {
                listener.actionPerformed(e);
                final String user = usernameField.getText();
                final String password = passwordField.getText();

                //向数据库验证用户名和密码
                u.log(user);
                u.log(password);
                if (true) {
                    w.dispose();
                    listener.actionPerformed(new ActionEvent(this, 0, "login" + user));
                } else {
                    errorMessage.setText("用户名或密码错误");
                }
            });

            w.add(fieldPanel);
            w.add(loginButton);
            w.add(errorMessage);
            w.done();
        }

        public LoginView(ActionListener listener) {
            this("", listener);
        }
    }

    public static class RegisterView {
        final int width = 330;
        iWindow w = new iWindow("新用户注册", width, 300);
        iButton loginButton = new iButton("注册", iButton.ButtonType.SMALL);
        String username;
        String password;
        iField usernameField = new iField("用户名", width, null);
        iField passwordField1 = new iField("密码", width, null);
        iField passwordField2 = new iField("确认密码", width, null);
        iLabel errorMessage = new iLabel("");

        public RegisterView(ActionListener listener) {
            w.setLayout(null);
            loginButton.setBounds(123, 168, 100, 30);
            errorMessage.setBounds(0, 200, width, 50);
            errorMessage.setForeground(Color.RED);

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 1));
            fieldPanel.setBounds(0, 50, width, 100);
            fieldPanel.add(usernameField);
            fieldPanel.add(passwordField1);
            fieldPanel.add(passwordField2);

            loginButton.addListener(e -> {
                listener.actionPerformed(e);
                final String user = usernameField.getText();
                final String password = passwordField1.getText();

                //向数据库储存用户名和密码
                u.log(user);
                u.log(password);
                if (true) {
                    w.dispose();
                    listener.actionPerformed(new ActionEvent(this, 0, "register" + user));
                } else {
                    errorMessage.setText("格式错误");
                }
            });

            w.add(fieldPanel);
            w.add(loginButton);
            w.add(errorMessage);
            w.done();
        }
    }

    // 测试
    public static void main(String[] args) {
        new LoginView(null);
        new RegisterView(null);
    }
}
