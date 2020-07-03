import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void init() throws Exception
    {
        System.out.println("On init"); //fixme
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        System.out.println("On start"); //fixme

        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        System.out.println("On stop"); //fixme
    }
}