package common;

import frame.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

                JFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            } catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}
