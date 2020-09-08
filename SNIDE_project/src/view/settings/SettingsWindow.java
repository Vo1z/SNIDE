package view.settings;

import autilities.SnideThemes;
import autilities.WindowType;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsWindow
{
    private MainController mainController;

    //Scene elements
    private Scene settingsScene;
    private Pane root;

    //OptionBoxes
    private OptionHBox doesLogSystemWork;
    private OptionHBox option2;
    private OptionHBox option3;
    private OptionHBox option4;

    public SettingsWindow(MainController mainController)
    {
        this.mainController = mainController;

        this.root = createRootPane();
        this.settingsScene = new Scene(this.root);
    }

    private Pane createRootPane()
    {
        VBox rootPane = new VBox();

        //Root pane configuration
        rootPane.getStylesheets().add(SnideThemes.SETTINGS_WINDOW_THEME_1);

        //Adding to pane
        rootPane.getChildren().addAll(createOptions());
        rootPane.getChildren().add(createBottomButtons());

        return rootPane;
    }

    private Pane createOptions()
    {
        VBox options = new VBox();

        this.doesLogSystemWork = new OptionHBox("Turn off log system"); //doesLogSystemWork
        this.option2 = new OptionHBox("Option 2");
        this.option3 = new OptionHBox("Option 3");
        this.option4 = new OptionHBox("Option 4");

        //Adding elements to pane
        options.getChildren().addAll(doesLogSystemWork, option2, option3, option4);

        return options;
    }

    private Pane createBottomButtons()
    {
        HBox buttonButtonsPane = new HBox();
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");

        //Configuration
        saveButton.setOnAction(e -> System.out.println("Not implemented")); //TODO

        backButton.setOnAction(
                e ->
                {
                    if (this.mainController.getPreviousWindowType() == WindowType.LAUNCH_WINDOW)
                    {
                        this.mainController.openLaunchWindow();
                    }
                    else if (this.mainController.getPreviousWindowType() == WindowType.EDITOR_WINDOW)
                    {
                        this.mainController.openEditorWindow();
                    }
                }
        );

        //Adding to pane
        buttonButtonsPane.getChildren().add(saveButton);
        buttonButtonsPane.getChildren().add(backButton);

        return buttonButtonsPane;
    }

    public Scene getConfiguredSettingsScene()
    {
        return this.settingsScene;
    }

    //Getters
    public boolean getDoesLogSystemWork()
    {
        return this.doesLogSystemWork.isChecked();
    }

    public boolean getOption2()
    {
        return this.option2.isChecked();
    }

    public boolean getOption3()
    {
        return this.option3.isChecked();
    }

    public boolean getOption4()
    {
        return this.option4.isChecked();
    }
}