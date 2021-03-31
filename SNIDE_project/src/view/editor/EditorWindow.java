package view.editor;

import snideUtilities.SnideThemes;
import snideUtilities.SnideUtils;
import controller.editor.EditorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        this.root = createCompleteRootPane();
        this.editorScene = new Scene(root);
    }

    private Pane createCompleteRootPane()
    {
        HBox root = new HBox(10);

        //Configuration of root
        root.getStylesheets().add(SnideThemes.COMPLETE_EDITOR_THEME_1);

        //Configuration of tab panel
        this.tabPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        //Adding to root
        root.getChildren().add(createCompleteControlPanel());
        root.getChildren().add(tabPanel);

        return root;
    }

    private Pane createSmartRootPane()
    {
        GridPane root = new GridPane();

        //Configuration of root
        root.getStylesheets().add(SnideThemes.SMART_EDITOR_THEME_1);

        //Configuration of tab panel
        this.tabPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        //Adding to root
        root.add(createSmartControlPanel(), 0, 0);
        root.add(tabPanel, 0 , 1);

        return root;
    }

    private Pane createCompleteControlPanel()
    {
        VBox controlPanel = new VBox();

        Button newFileButton = new Button("New");
        Button openButton = new Button("Open");
        Button saveButton = new Button("Save");
        //Button enterSmartModeButton = new Button("Smart");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        //Buttons configuration
        newFileButton.setOnAction(e -> this.editorController.createNewFile());

        openButton.setOnAction(e -> this.editorController.getMainController().openFileChooserAndAddChosenFilesToEditor());

        saveButton.setOnAction(e -> this.editorController.getEditorModel().saveFile(this.tabPanel.getSelectionModel().getSelectedIndex()));

        //enterSmartModeButton.setOnAction(e -> this.editorController.getMainController().openSmartEditorWindow());

        settingsButton.setOnAction(e -> this.editorController.getMainController().openSettingsWindow());

        exitButton.setOnAction(e -> SnideUtils.stopProgram());

        //Adding to pane
        controlPanel.getChildren().add(newFileButton);
        controlPanel.getChildren().add(openButton);
        controlPanel.getChildren().add(saveButton);
        //controlPanel.getChildren().add(enterSmartModeButton);
        controlPanel.getChildren().add(settingsButton);
        controlPanel.getChildren().add(exitButton);

        return controlPanel;
    }

    private Pane createSmartControlPanel()
    {
        HBox controlPanel = new HBox();

        Button newFileButton = new Button("New");
        Button openButton = new Button("Open");
        Button saveButton = new Button("Save");
        Button enterCompleteModeButton = new Button("Complete");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        //Buttons configuration
        newFileButton.setOnAction(e -> this.editorController.createNewFile());

        openButton.setOnAction(e -> this.editorController.getMainController().openFileChooserAndAddChosenFilesToEditor());

        saveButton.setOnAction(e -> this.editorController.getEditorModel().saveFile(this.tabPanel.getSelectionModel().getSelectedIndex()));

        enterCompleteModeButton.setOnAction(e -> this.editorController.getMainController().openCompleteEditorWindow());

        settingsButton.setOnAction(e -> this.editorController.getMainController().openSettingsWindow());

        exitButton.setOnAction(e -> SnideUtils.stopProgram());

        //Adding to pane
        controlPanel.getChildren().add(newFileButton);
        controlPanel.getChildren().add(openButton);
        controlPanel.getChildren().add(saveButton);
        controlPanel.getChildren().add(enterCompleteModeButton);
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
                                SnideUtils.printDebug("Adding to VIEW" + " \"" +editorTab.getText() + "\""); //fixme debug
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
                                SnideUtils.printDebug("Removing to VIEW" + " \"" +tab.getText() + "\""); //fixme debug
                            }
                        }
                );
    }

    //Getters
    public Scene getCompleteEditorScene()
    {
        this.root = createCompleteRootPane();
        this.editorScene.setRoot(root);

        return this.editorScene;
    }

    public Scene getSmartEditorScene()
    {
        this.root = createSmartRootPane();
        this.editorScene.setRoot(root);

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