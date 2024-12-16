import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.application.Platform;

class HtmlPanel extends JFXPanel {
    private WebView view;
    public HtmlPanel() {
        super();
        Platform.runLater(() -> {
            view = new WebView();
            view.setContextMenuEnabled(false);
            view.getEngine().setOnError(e -> System.err.println(e.getMessage()));
            view.getEngine().setOnAlert(e -> System.out.println(e.getData()));
            
            setScene(new Scene(view));
        });
    }
    public void loadUrl(String url) {
        Platform.runLater(() -> {
            view.getEngine().load(url);
        });
    }
    public void load(String content) {
        Platform.runLater(() -> {
            view.getEngine().loadContent(content);
        });
    }
}