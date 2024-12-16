import javax.swing.*;

class TButton extends JButton {
    public TButton(String title, ImageIcon im) {
        setIcon(im);
        setToolTipText(title);
    }
}