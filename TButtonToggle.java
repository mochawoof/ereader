import javax.swing.*;
import java.awt.event.*;
import java.util.function.Consumer;

class TButtonToggle extends TButton {
    private boolean toggled = false;
    private Consumer<Boolean> onToggled;
    
    private String title; private String titlea; // When not toggled
    private ImageIcon im; private ImageIcon ima; // When toggled
    
    public TButtonToggle(String title, String titlea, ImageIcon im, ImageIcon ima) {
        super(titlea, ima);
        this.title = title; this.titlea = titlea;
        this.im = im; this.ima = ima;
        
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggled = !toggled;
                update();
                onToggled.accept(toggled);
            }
        });
    }
    private void update() {
        // Reflect the wanted state, not the current state
        if (!toggled) {
            setToolTipText(titlea);
            setIcon(ima);
        } else {
            setToolTipText(title);
            setIcon(im);
        }
    }
    public boolean isToggled() {
        return toggled;
    }
    public void setOnToggled(Consumer<Boolean> r) {
        onToggled = r;
    }
    public void toggle(boolean value) {
        toggled = value;
        update();
    }
}