package controller;

import autilities.Consts;
import autilities.ElementsTicker;
import autilities.Utils;
import autilities.WindowType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.EditorModel;
import view.EditorWindow;
import view.LaunchWindow;
import view.SettingsWindow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainController extends Application
{
    private EditorWindow editorWindow;
    private EditorModel editorModel;
    private LaunchWindow launchWindow;
    private SettingsWindow settingsWindow;

    private Stage mainStage;
    private ElementsTicker<WindowType> windowHist;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void init() throws Exception
    {
        System.out.println("On init"); //fixme debug
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        System.out.println("On start"); //fixme debug

        this.mainStage = stage;
        this.editorWindow = new EditorWindow(this);
        this.editorModel = new EditorModel(this);
        this.launchWindow = new LaunchWindow(this);
        this.settingsWindow = new SettingsWindow(this);
        this.windowHist = new ElementsTicker<>(WindowType.values().length);


        this.mainStage.setTitle("SNIDE");
        this.openLaunchWindow();
//        this.mainStage.initStyle(StageStyle.UNDECORATED);
        this.mainStage.show();
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("On stop"); //fixme debug
    }

    //Ide Window
    public void openLaunchWindow()
    {
        this.windowHist.push(WindowType.LAUNCH_WINDOW);

        this.mainStage.setResizable(false);
        this.mainStage.setWidth(400);
        this.mainStage.setHeight(600);

        this.mainStage.setScene(launchWindow.getConfiguredLaunchScene());
        this.mainStage.hide();
        this.mainStage.show();
    }

    //Launch window
    public void openEditorWindow()
    {
        this.windowHist.push(WindowType.EDITOR_WINDOW);

        this.mainStage.setResizable(true);
        this.mainStage.setHeight(Consts.DEFAULT_WINDOW_HEIGHT);
        this.mainStage.setWidth(Consts.DEFAULT_WINDOW_WIDTH);

        this.mainStage.setScene(editorWindow.getConfiguredEditorScene());
        this.mainStage.hide();
        this.mainStage.show();
    }

    public void openSettingsWindow()
    {
        this.windowHist.push(WindowType.SETTINGS_WINDOW);

        this.mainStage.setResizable(false);
        this.mainStage.setWidth(400);
        this.mainStage.setHeight(600);

        this.mainStage.setScene(settingsWindow.getConfiguredSettingsScene());
        this.mainStage.hide();
        this.mainStage.show();
    }

    public void openGitHubInBrowser()
    {
        try
        {
            new ProcessBuilder("x-www-browser", Consts.GitHub_URL).start();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public Scene getCurrentScene()
    {
        return this.mainStage.getScene();
    }

    public void openFileChooserAndAddChosenFilesToEditor()
    {
        List<File> chosenFiles = Utils.getFilesFromFileChooser(this.mainStage);

        //Adding tabs to model
        for (var file : chosenFiles)
        {
            this.editorModel.addFile(file);
        }

        //Adding tabs to view
        for (var fileKey : this.editorModel.getOpenedFiles().keySet())
        {
            this.editorWindow.addTab(fileKey, this.editorModel.getOpenedFiles().get(fileKey));
        }
    }

    public void closeTab(int fromIndex, int toIndex)
    {
        System.err.println("Closing tap from controller");

        this.editorWindow.getTabPanel().getTabs().remove(fromIndex, toIndex);
        this.editorModel.removeFile(fromIndex, toIndex);
    }

    //Getters
    public Stage getMainStage()
    {
        return this.mainStage;
    }

    public Object getPreviousWindowType()
    {
        if (this.windowHist.getElements().length >= 2)
        {
            return windowHist.getElements()[windowHist.getElements().length - 2];
        }
        else
        {
            return null;
        }
    }

    public Object getCurrentWindowType()
    {
        if (this.windowHist.getElements().length >= 1)
        {
            return windowHist.getElements()[windowHist.getElements().length - 1];
        }
        else
        {
            return  null;
        }
    }
}