package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class u {
    public static ImageIcon getImageIcon(String name) {
        String path;
        if (fileExist("resource/icon/" + name + ".png"))
            path = "resource/icon/" + name + ".png";
        else if (fileExist("resource/img/" + name))
            path = "resource/img/" + name;
        else {
            path = "src/resource/img/404.png";
            System.err.println("File not found: " + path);
        }
        return new ImageIcon(path);
    }

    public static ImageIcon getImageIcon(String name, int width, int height) {
        return new ImageIcon(getImageIcon(name).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static boolean fileExist(String path) {
        return new File(path).exists();
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void err(String msg) {
        System.err.println(msg);
    }
}
