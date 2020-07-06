package MainController;

import ApplicationWindow.IdeWindow;
import LaunchWindow.LaunchWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainController extends Application
{
    private Stage mainStage;
    private IdeWindow ideWindow;
    private LaunchWindow launchWindow;

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
        this.ideWindow = new IdeWindow(this);
        this.launchWindow = new LaunchWindow(this);


        this.mainStage.setScene(launchWindow.getLaunchScene());
        this.mainStage.setTitle("SNIDE");
        this.mainStage.show();
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("On stop"); //fixme debug
    }

    //Getters
    public Stage getMainStage()
    {
        return this.mainStage;
    }

    public IdeWindow getIdeWindow()
    {
        return this.ideWindow;
    }

    public LaunchWindow getLaunchScene()
    {
        return this.launchWindow;
    }
}