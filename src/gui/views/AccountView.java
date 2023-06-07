package gui.views;

import gui.components.iButton;
import gui.components.iField;
import gui.components.iLabel;
import gui.components.iWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sql.UserController;
import sql.UserItem;
import utils.u;
import gui.components.iDialog;

import static gui.actions.actionPopup;

public class AccountView {
    public static class LoginView {
        final int width = 300;
        iWindow w = new iWindow("用户登录", width, 300);

        public LoginView(String username, ActionListener listener) {
            iButton loginButton = new iButton("登录", iButton.ButtonType.SMALL);
            iField usernameField = new iField("用户名", width);
            iField passwordField = new iField("密码", width);
            iLabel errorMessage = new iLabel("");

            w.setLayout(null);
            loginButton.setBounds(100, 168, 100, 30);
            errorMessage.setBounds(0, 200, width, 50);
            errorMessage.setForeground(Color.RED);
            errorMessage.setCenter();

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(2, 1));
            fieldPanel.setBounds(0, 50, width, 100);

            usernameField.setText(username);
            fieldPanel.add(usernameField);
            fieldPanel.add(passwordField);

            loginButton.addActionListener(e -> {
                final String user = usernameField.getText();
                final String password = passwordField.getText();

                //向数据库验证用户名和密码
                if (UserController.check(user, password)) {
                    iDialog.dialogMessage(null, "登录成功", "欢迎回来，" + user + "！");
                    actionPopup(w, listener, 0,
                            user.equals("root") ? "root" : "user"
                    );
                    w.dispose();
                } else {
                    errorMessage.setText("用户名或密码错误!");
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
        iField usernameField = new iField("用户名", width);
        iField passwordField1 = new iField("密码", width);
        iField passwordField2 = new iField("确认密码", width);
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
                final String password1 = passwordField1.getText();
                final String password2 = passwordField2.getText();

                if (!password1.equals(password2)) {
                    errorMessage.setText("两次密码不一致！");
                    return;
                }
                if (user.equals("") || password1.equals("")) {
                    errorMessage.setText("用户名或密码不能为空！");
                    return;
                }
                if (user.length() < 3 || user.length() > 20) {
                    errorMessage.setText("用户名长度应在3-20之间！");
                    return;
                }

                //向数据库储存用户名和密码
                if (UserController.create(new UserItem(0, user, password1, null, "")).equals("success")) {
                    iDialog.dialogMessage(null, "完成！", "注册成功！去登录吧～");
                    w.dispose();
                } else {
                    errorMessage.setText("用户已存在！");
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
