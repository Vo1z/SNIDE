import LaunchWindow.Launch;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    private Launch launchScene;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void init() throws Exception
    {
        System.out.println("On init"); //fixme
        this.launchScene = new Launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        System.out.println("On start"); //fixme

        stage.setScene(launchScene.getLaunchScene());
        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("On stop"); //fixme
    }
}