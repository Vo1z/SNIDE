package view;

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

    //Scene elements
    private Scene editorScene;
    private Pane root;

    public EditorWindow(MainController mainController)
    {
        //Assign
        this.mainController = mainController;

        this.root = createRootPane();
        this.editorScene = new Scene(root);
    }

    private Pane createRootPane()
    {
        HBox root = new HBox(10);

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

        settingsButton.setOnAction(e -> this.mainController.openSettingsWindow());

        exitButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        //Adding to pane
        topPanelPane.getChildren().add(addNewFileButton);
        topPanelPane.getChildren().add(saveButton);
        topPanelPane.getChildren().add(loadButton);
        topPanelPane.getChildren().add(settingsButton);
        topPanelPane.getChildren().add(exitButton);

        return topPanelPane;
    }

    private Pane createTextEditorPanel()
    {
        StackPane stackPane = new StackPane();
        TabPane tabPane = new TabPane();

        Tab tab = new Tab("FirstTab", new TextArea());

        tabPane.getTabs().add(tab);
        stackPane.getChildren().add(tabPane);

        return stackPane;
    }

    //Getters
    public Scene getEditorScene()
    {
        return editorScene;
    }

    public Scene getConfiguredEditorScene()
    {
        return editorScene;
    }
}