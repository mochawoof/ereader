import javax.swing.*;
import java.awt.event.*;
import java.util.function.Consumer;

class TButtonToggle extends TButton {
    private boolean toggled = false;
    private Consumer<Boolean> onToggled;
    public TButtonToggle(String title, String titlea, ImageIcon im, ImageIcon ima) {
        super(title, im);
        
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!toggled) {
                    setToolTipText(titlea);
                    setIcon(ima);
                } else {
                    setToolTipText(title);
                    setIcon(im);
                }
                toggled = !toggled;
                onToggled.accept(toggled);
            }
        });
    }
    public boolean isToggled() {
        return toggled;
    }
    public void setOnToggled(Consumer<Boolean> r) {
        onToggled = r;
    }
}