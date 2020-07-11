package ApplicationWindow;

import Constants.Consts;
import MainController.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class IdeWindow
{
    private MainController mainController;

    private Scene editorScene;
    private Pane root;

    //Scene elements
    private TextEditorArea textEditorArea;

    public IdeWindow(MainController mainController)
    {
        //Assign
        this.mainController = mainController;

        this.root = createRootPane();
        this.editorScene = new Scene(root); //TODO change to root

        //Scene configuration
        this.mainController.getMainStage().setResizable(true);
        this.mainController.getMainStage().setHeight(Consts.DEFAULT_WINDOW_HEIGHT);
        this.mainController.getMainStage().setWidth(Consts.DEFAULT_WINDOW_WIDTH);
    }

    private Pane createRootPane()
    {
        //Assign
        StackPane createdRoot = new StackPane();
        this.textEditorArea = new TextEditorArea();

        createdRoot.getChildren().add(textEditorArea);

        return createdRoot;
    }

    //Getters
    public Scene getEditorScene()
    {
        return editorScene;
    }

    public Scene getConfiguredEditorScene()
    {
        this.mainController.getMainStage().setResizable(true);
        this.mainController.getMainStage().setHeight(Consts.DEFAULT_WINDOW_HEIGHT);
        this.mainController.getMainStage().setWidth(Consts.DEFAULT_WINDOW_WIDTH);

        return editorScene;
    }
}