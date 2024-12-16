import javax.swing.*;

class CleanScrollPane extends JScrollPane {
    public CleanScrollPane(JComponent c) {
        super(c);
        setBorder(BorderFactory.createEmptyBorder());
    }
    public CleanScrollPane() {
        super();
        setBorder(BorderFactory.createEmptyBorder());
    }
}