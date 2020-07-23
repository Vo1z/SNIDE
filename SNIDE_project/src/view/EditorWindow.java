package view;

import autilities.Themes;
import controller.EditorController;
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
    private EditorController editorController;

    //Scene elements
    private Scene editorScene;
    private Pane root;
    private TabPane tabPanel;

    public EditorWindow(EditorController editorController)
    {
        //Assign
        this.editorController = editorController;
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

        tab.setOnClosed(e -> this.editorController.removeFileFromEditor(this.tabPanel.getSelectionModel().getSelectedIndex()));
        this.tabPanel.getTabs().add(tab);

        System.err.println("Tab was added under index " + this.tabPanel.getTabs().indexOf(tab));//fixme debug
    }

    public void removeTab(int index)
    {
        System.err.println("Tab " + tabPanel.getTabs().get(index).getId() + " was removed from the EditorWindow");
        this.tabPanel.getTabs().remove(index);
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