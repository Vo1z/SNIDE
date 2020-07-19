package autilities;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    public static ArrayList<String> getWordsFromFile(final String path)
    {
        ArrayList<String> words = new ArrayList<>();
        String fileContent = "";

        //Reading file
        try
        {
            FileInputStream fis = new FileInputStream(path);
            int idChar;
            while ((idChar = fis.read()) != -1)
            {
                fileContent += (char)idChar;
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException ioException) { ioException.printStackTrace(); }

        //Finding words
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find())
        {
            words.add(matcher.group());
        }

        return words;
    }

    public static List<File> getFilesFromFileChooser(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select files to open");

        return fileChooser.showOpenMultipleDialog(stage);
    }
}
