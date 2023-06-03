package gui;

import gui.components.iLabel;
import gui.components.iWindow;
import utils.u;

import javax.swing.*;
import java.awt.*;


public class Sys {
    Sys() {
        iWindow w = new iWindow("欢迎", 500, 500);
        w.setWelcomeMenuBar();
        w.setLayout(new BorderLayout());

        iLabel welcomeImage = new iLabel("欢迎使用！", "car-rental.png", 200, 200, iLabel.VERTICAL);
        welcomeImage.setCenter();

        w.add(welcomeImage, BorderLayout.CENTER);
        w.visible();
    }


    public static void main(String[] args) {
        new Sys();
    }
}
