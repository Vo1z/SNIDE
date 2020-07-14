package view;

import autilities.Consts;
import autilities.Themes;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EditorWindow
{
    private MainController mainController;

    private Scene editorScene;
    private Pane root;

    //Scene elements

    public EditorWindow(MainController mainController)
    {
        //Assign
        this.mainController = mainController;

        this.root = createRootPane();
        this.editorScene = new Scene(root);
    }

    private Pane createRootPane()
    {
        HBox root = new HBox();

        //Configuration of root
        root.getStylesheets().add(Themes.IDE_WINDOW_THEME_1);

        //Adding to root
        root.getChildren().add(createControlPanel());
        root.getChildren().add(createTextEditorPanel());

        return root;
    }

    private Pane createControlPanel()
    {
        VBox topPanelPane = new VBox();
        Button addNewFileButton = new Button("New");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        //Buttons configuration
        addNewFileButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        saveButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        loadButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        settingsButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        exitButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        //Adding to pane
        topPanelPane.getChildren().add(addNewFileButton);
        topPanelPane.getChildren().add(saveButton);
        topPanelPane.getChildren().add(loadButton);
        topPanelPane.getChildren().add(settingsButton);
        topPanelPane.getChildren().add(exitButton);

        return topPanelPane;
    }

    private TabPane createTextEditorPanel()
    {
        TabPane tabPane = new TabPane();

        Tab tab = new Tab("firstTab", new TextArea());

        tabPane.getTabs().add(tab);

        return tabPane;
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