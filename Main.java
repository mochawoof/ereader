import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;

class Main {
    public static void main(String[] args) {
        try {
            String laf = UIManager.getSystemLookAndFeelClassName();
            switch (Settings.get("Theme")) {
                case "Cross-Platform":
                    laf = UIManager.getCrossPlatformLookAndFeelClassName();
                    break;
                case ""
            }
        }

        JFrame f = new JFrame();
        f.setSize(600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        f.setVisible(true);
    }
}