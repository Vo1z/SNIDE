package autilities;

import javafx.stage.FileChooser;
import javafx.stage.Screen;

public class SnideConsts
{
    //Web links and file path
    public final static String GitHub_URL = "https://github.com/Vo1z/SNIDE";
    public final static String JAVA_KEYWORDS_PATH = "res/java_keywords";
    public final static String LOG_FILE_PATH = "logs/log.txt";
    public final static String SETTINGS_FILE_PATH = "res/settings.txt";

    //Window properties
    public final static double DEFAULT_WINDOW_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public final static double DEFAULT_WINDOW_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    //Editor
    public static final double SMART_MODE_EDITOR_WINDOW_WIDTH = 600;
    public static final double SMART_MODE_EDITOR_WINDOW_HEIGHT = 800;

    //Settings
    public static final double SETTINGS_WINDOW_WIDTH = 400;
    public static final double SETTINGS_WINDOW_HEIGHT = 600;

    //File chooser
    public final static FileChooser.ExtensionFilter FILES_POOL_EXTENSION = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");//FIXME
}
