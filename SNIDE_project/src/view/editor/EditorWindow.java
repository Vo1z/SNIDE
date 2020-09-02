package view.editor;

import autilities.SnideThemes;
import autilities.SnideUtils;
import controller.editor.EditorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.editor.EditorTab;

public class EditorWindow
{
    private EditorController editorController;

    //Scene elements
    private TabPane tabPanel;

    private Scene editorScene;
    private Pane root;

    public EditorWindow(EditorController editorController)
    {
        //Assign
        this.editorController = editorController;
        this.tabPanel = new TabPane();
        this.root = createRootPane();
        this.editorScene = new Scene(root);
    }

    private Pane createRootPane()
    {
        HBox root = new HBox(10);

        //Configuration of root
        root.getStylesheets().add(SnideThemes.IDE_WINDOW_THEME_1);

        //Configuration of tab panel
        this.tabPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        //Adding to root
        root.getChildren().add(createControlPanel());
        root.getChildren().add(tabPanel);

        return root;
    }

    private Pane createControlPanel()
    {
        VBox controlPanel = new VBox();
        Button addNewFileButton = new Button("New");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Open");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        //Buttons configuration
        addNewFileButton.setOnAction(e -> this.editorController.createNewFile());

        saveButton.setOnAction(e -> this.editorController.getEditorModel().saveFile(this.tabPanel.getSelectionModel().getSelectedIndex()));

        loadButton.setOnAction(e -> this.editorController.getMainController().openFileChooserAndAddChosenFilesToEditor());

        settingsButton.setOnAction(e -> this.editorController.getMainController().openSettingsWindow());

        exitButton.setOnAction(e -> SnideUtils.stopProgram());

        //Adding to pane
        controlPanel.getChildren().add(addNewFileButton);
        controlPanel.getChildren().add(saveButton);
        controlPanel.getChildren().add(loadButton);
        controlPanel.getChildren().add(settingsButton);
        controlPanel.getChildren().add(exitButton);

        return controlPanel;
    }

    public void updateTabs()
    {
        this.editorController.getEditorModel().getEditorTabs().stream()
                .forEach(
                        editorTab ->
                        {
                            if (!this.tabPanel.getTabs().contains(editorTab))
                            {
                                this.tabPanel.getTabs().add(editorTab);
                                SnideUtils.printDebug("Adding to VIEW"); //fixme debug
                            }
                        }
                );

        this.tabPanel.getTabs().stream()
                .forEach(
                        tab ->
                        {
                            if (!this.editorController.getEditorModel().getEditorTabs().contains(tab))
                            {
                                this.tabPanel.getTabs().remove(tab);
                                SnideUtils.printDebug("Removing to VIEW"); //fixme debug
                            }
                        }
                );
    }

    //Getters
    public Scene getConfiguredEditorScene()
    {
        return this.editorScene;
    }

    public TabPane getTabPanel()
    {
        return this.tabPanel;
    }

    public EditorTab getSelectedEditorTab()
    {
        return (EditorTab) this.tabPanel.getSelectionModel().getSelectedItem();
    }

}