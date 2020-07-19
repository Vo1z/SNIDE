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

import java.io.File;

public class EditorWindow
{
    private MainController mainController;

    //Scene elements
    private Scene editorScene;
    private Pane root;
    private TabPane tabPanel;

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

        loadButton.setOnAction(e -> mainController.openFileChooserAndAddChosenFilesToEditor()); //TODO

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
        tabPanel = new TabPane();

        stackPane.getChildren().add(tabPanel);

        return stackPane;
    }

    public void addTab(File file, String content)
    {
        Tab tab = new Tab(file.getName(), new TextArea(content));

        this.tabPanel.getTabs().add(tab);

        int tabIndex = this.tabPanel.getTabs().indexOf(tab);
        tab.setOnClosed(e -> this.mainController.closeTab(tabIndex, tabIndex));
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

    public TabPane getTabPanel()
    {
        return tabPanel;
    }
}