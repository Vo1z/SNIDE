package autilities;

import javafx.stage.FileChooser;
import javafx.stage.Screen;

public class Consts
{
    //Web links and file path
    public final static String GitHub_URL = "https://github.com/Vo1z/SNIDE";
    public final static String JAVA_KEYWORDS_PATH = "res/java_keywords";

    //Window properties
    public final static double DEFAULT_WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public final static double DEFAULT_WINDOW_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    //File chooser
    public final static FileChooser.ExtensionFilter FILES_POOL_EXTENSION = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");//FIXME
}
