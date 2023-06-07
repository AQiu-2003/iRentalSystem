package gui;

import gui.components.iLabel;
import gui.components.iWindow;
import utils.u;

import javax.swing.*;
import java.awt.*;
import java.security.PublicKey;


public class Sys {
    public Sys() {
        iWindow w = new iWindow("欢迎", 800, 800, true);
//        w.setWelcomeMenuBar();
        w.setLayout(new BorderLayout());

        iLabel welcomeImage = new iLabel(
                "欢迎使用！",
                "car-rental.png",
                200, 200,
                iLabel.VERTICAL
        );
        welcomeImage.setFont(new Font("微软雅黑", Font.BOLD, 20));
        welcomeImage.setCenter();
        w.add(welcomeImage, BorderLayout.CENTER);
        w.done();
    }


    public static void main(String[] args) {
        new Sys();
    }
}
