package controller;

import autilities.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.launch.LaunchWindow;
import view.settings.SettingsWindow;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController extends Application
{
    private EditorController editorController;

    private LaunchWindow launchWindow;
    private SettingsWindow settingsWindow;
    private ThreadManager threadManager;

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

        //Editor
        this.editorController = new EditorController(this);

        this.launchWindow = new LaunchWindow(this);
        this.settingsWindow = new SettingsWindow(this);
        this.threadManager = new ThreadManager(this);
        this.windowHist = new ElementsTicker<>(WindowType.values().length);

        //Stage configuration
        //this.mainStage.initStyle(StageStyle.UNDECORATED);
        this.mainStage.setTitle("SNIDE");
        this.openLaunchWindow();
        this.mainStage.show();
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("On stop"); //fixme debug
    }

    public void openLaunchWindow()
    {
        this.windowHist.push(WindowType.LAUNCH_WINDOW);
        this.mainStage.hide();

        this.mainStage.setResizable(false);
        this.mainStage.setMaximized(false);
        this.mainStage.setWidth(400);
        this.mainStage.setHeight(600);

        this.mainStage.setScene(launchWindow.getConfiguredLaunchScene());
        this.mainStage.show();
    }

    public void openEditorWindow()
    {
        this.windowHist.push(WindowType.EDITOR_WINDOW);
        this.mainStage.hide();

        this.mainStage.setResizable(true);
        //this.mainStage.setHeight(Consts.DEFAULT_WINDOW_HEIGHT);
        //this.mainStage.setWidth(Consts.DEFAULT_WINDOW_WIDTH);
        this.mainStage.setMaximized(true);

        this.mainStage.setScene(this.editorController.getEditorWindow().getConfiguredEditorScene());
        this.mainStage.show();
    }

    public void openSettingsWindow()
    {
        this.windowHist.push(WindowType.SETTINGS_WINDOW);
        this.mainStage.hide();

        this.mainStage.setResizable(false);
        this.mainStage.setMaximized(false);
        this.mainStage.setWidth(400);
        this.mainStage.setHeight(600);

        this.mainStage.setScene(settingsWindow.getConfiguredSettingsScene());
        this.mainStage.show();
    }

    public void openGitHubInBrowser()
    {
        try
        {
            new ProcessBuilder("x-www-browser", SnideConsts.GitHub_URL).start();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public void openEditorWindowWithNewFile()
    {
        this.openEditorWindow();
        this.editorController.createNewFile();
    }

    public void openFileChooserAndAddChosenFilesToEditor()
    {
        List<File> chosenFiles = SnideUtils.getFilesFromFileChooser(this.mainStage);

        if(chosenFiles != null)
        {
            chosenFiles.stream().forEach( file -> this.editorController.addFileToEditor(file) );
        }
    }

    //Getters

    public Scene getCurrentScene()

    {
        return this.mainStage.getScene();
    }

    public Stage getMainStage()
    {
        return this.mainStage;
    }

    public EditorController getEditorController()
    {
        return  this.editorController;
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