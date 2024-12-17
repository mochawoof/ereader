import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main {
    private static JFrame f;
    private static JToolBar tb;
    
    private static TButton h;
    private static TButton lp;
    private static TButton np;
    private static TButtonToggle pa;
    private static TButton hi;
    private static TButton s;
    private static TButton ab;
    
    private static JPanel card;
    private static CardLayout cl;
    
    private static JPanel shelf;
    private static JPanel read;
    private static HtmlPanel leftpage;
    private static HtmlPanel rightpage;
    
    private static void setLaf(String lafClass) {
        try {
            UIManager.setLookAndFeel(lafClass);
            
            SwingUtilities.updateComponentTreeUI(f);
            f.revalidate();
            f.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);        
        
        JFrame mf = new JFrame("Bookshelf");
        mf.setSize(400, 600);
        
        // Set old frame properties when restarting
        if (f != null) {
            mf.setLocation(f.getLocation());
            mf.setSize(f.getSize());
            f.dispose();
        }
        
        f = mf;
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setIconImage(Res.getAsImage("res/icon.png"));
        setLaf("com.seaglasslookandfeel.SeaGlassLookAndFeel");

        addComponents();
        addEvents();

        f.setVisible(true);
    }
    private static void addComponents() {
        // Menu stuff
        tb = new JToolBar("Menu");
        f.add(tb, BorderLayout.PAGE_START);
        
        h = new TButton("Home", Res.getAsImageIcon("res/home.png"));
        tb.add(h);
        
        lp = new TButton("Last Page", Res.getAsImageIcon("res/lastpage.png"));
        tb.add(lp);
        
        np = new TButton("Next Page", Res.getAsImageIcon("res/nextpage.png"));
        tb.add(np);
        
        pa = new TButtonToggle("Two Pages", "One Page", Res.getAsImageIcon("res/twopages.png"), Res.getAsImageIcon("res/onepage.png"));
        tb.add(pa);
        
        hi = new TButton("History", Res.getAsImageIcon("res/history.png"));
        tb.add(hi);
        
        s = new TButton("Settings", Res.getAsImageIcon("res/settings.png"));
        tb.add(s);
        
        ab = new TButton("About", Res.getAsImageIcon("res/about.png"));
        tb.add(ab);
        
        // Card layout
        card = new JPanel();
        cl = new CardLayout();
        card.setLayout(cl);
        f.add(card, BorderLayout.CENTER);
        
        shelf = new JPanel();
        card.add("Shelf", new CleanScrollPane(shelf));
        
        read = new JPanel();
        read.setLayout(new GridLayout(1, 0));

        leftpage = new HtmlPanel();
        leftpage.loadUrl("http://example.com");
        read.add(leftpage);
        rightpage = new HtmlPanel();
        rightpage.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
        rightpage.loadUrl("http://example.com");
        
        card.add("Read", read);
        
        cl.last(card);
    }
    private static void updateReadPages() {
        if (pa.isToggled()) {
            read.remove(rightpage);
        } else {
            read.add(rightpage);
        }
        read.revalidate();
        read.repaint();
    }
    private static void addEvents() {
        h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.first(card);
            }
        });
        // Set stored pa toggle
        switch (Settings.get(".Pa")) {
            case "1":
                pa.toggle(true);
                break;
            default:
                pa.toggle(false);
                break;
        }
        updateReadPages(); // Update them read pages ahead of time
        pa.setOnToggled((t) -> {
            if (!t) {
                Settings.set(".Pa", "0");
            } else {
                Settings.set(".Pa", "1");
            }
            updateReadPages();
        });
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int s = Settings.showEditWindow(f);
                if (s == Settings.OK || s == Settings.RESET) {
                    main(new String[0]);
                }
            }
        });
        ab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Bookshelf Version 1.0 Prerelease.\n\nCopyright (c) SchizoSoft 2024. All rights reserved.", "About Bookshelf", JOptionPane.PLAIN_MESSAGE, new ImageIcon(f.getIconImage()));
            }
        });
    }
}