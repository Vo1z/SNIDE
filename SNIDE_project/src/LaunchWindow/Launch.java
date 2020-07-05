package LaunchWindow;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Launch
{
    private Pane root;
    private Scene launchScene;

    public Launch()
    {
        this.root = createRootPane();
        this.launchScene = new Scene(root);
    }

    private Pane createRootPane()
    {
        VBox createdRootPane = new VBox();

        Button button = new Button("Change scene");

        return createdRootPane;//fixme
    }

    public Scene getLaunchScene()
    {
        return this.launchScene;
    }
}
