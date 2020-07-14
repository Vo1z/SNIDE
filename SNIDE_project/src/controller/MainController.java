package controller;

import view.EditorWindow;
import autilities.Consts;
import view.LaunchWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application
{
    private EditorWindow editorWindow;
    private LaunchWindow launchWindow;

    private Stage mainStage;

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
        this.launchWindow = new LaunchWindow(this);

        this.mainStage.setScene(launchWindow.getConfiguredLaunchScene());
        this.mainStage.setTitle("SNIDE");
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
        this.mainStage.setScene(launchWindow.getConfiguredLaunchScene());
        this.mainStage.hide();
        this.mainStage.show();
    }

    //Launch window
    public void openIdeWindow()
    {
        this.mainStage.setScene(editorWindow.getConfiguredEditorScene());
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

    //Getters
    public Stage getMainStage()
    {
        return this.mainStage;
    }

    public EditorWindow getEditorWindow()
    {
        return this.editorWindow;
    }

    public LaunchWindow getLaunchWindow()
    {
        return this.launchWindow;
    }
}