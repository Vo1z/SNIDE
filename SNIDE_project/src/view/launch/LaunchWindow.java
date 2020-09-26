package view.launch;

import autilities.SnideThemes;
import autilities.SnideUtils;
import controller.editor.EditorController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LaunchWindow
{
    private EditorController editorController;

    private Pane root;
    private Scene launchScene;

    public LaunchWindow(EditorController editorController)
    {
        //Assign
        this.editorController = editorController;
        this.root = createRootPane();
        this.launchScene = new Scene(root);

        //Stage properties
        this.editorController.getMainController().getMainStage().setResizable(false);
        this.editorController.getMainController().getMainStage().setWidth(400);
        this.editorController.getMainController().getMainStage().setHeight(600);
    }

    private Pane createRootPane()
    {
        //Assign
        VBox createdRootPane = new VBox();
        Button createButton = new Button("Create");
        Button openButton = new Button("Open");
        Button settingsButton = new Button("Settings");
        Button gitHubButton = new Button("GitHub");
        Button exitButton = new Button("Exit");

        //Buttons configuration
        createButton.setOnAction(event -> this.editorController.getMainController().openEditorWindowWithNewFile());

        openButton.setOnAction(e ->
        {
            this.editorController.getMainController().openFileChooserAndAddChosenFilesToEditor();
            this.editorController.getMainController().openCompleteEditorWindow();
        }); //TODO

        settingsButton.setOnAction(e -> this.editorController.getMainController().openSettingsWindow());

        gitHubButton.setOnAction(e -> this.editorController.getMainController().openGitHubInBrowser());

        exitButton.setOnAction(e -> SnideUtils.stopProgram());

        //Adding to pane
        createdRootPane.getChildren().addAll(createButton, openButton, settingsButton, gitHubButton, exitButton);

        //Root pane configuration
        createdRootPane.getStylesheets().add(SnideThemes.LAUNCH_WINDOW_THEME_1);

        return createdRootPane;
    }

    //Getters
    public Scene getConfiguredLaunchScene()
    {
        return this.launchScene;
    }
}
