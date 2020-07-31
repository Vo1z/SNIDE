package autilities;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    public static String getFileContent(File inputFile)
    {
        StringBuffer fileContent = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(inputFile);
            int ind;

            while((ind = fis.read()) != -1)
            {
                fileContent.append((char)ind);
            }

            fis.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return fileContent.toString();
    }

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

            fis.close();
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
        fileChooser.setTitle("File manager"); //TODO think about better title
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        if (selectedFiles != null)
        {
            selectedFiles.stream()
                    .filter(file -> !file.isDirectory());
        }

        return  selectedFiles;
    }

    public static File getSavedFileFromFileChooser(Stage stage, String fileContent)
    {
        File fileToSave;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(Consts.FILES_POOL_EXTENSION);

        fileToSave = fileChooser.showSaveDialog(stage);

        try
        {
            FileWriter fw = new FileWriter(fileToSave);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(fileContent);
            bw.close();

            printDebug("File created: " + getFileInfo(fileToSave, "CREATED", true));//fixme debug
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return fileToSave;
    }

    public static void showAlertWindow(String alertMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(alertMessage);
        alert.showAndWait();
    }

    public static void stopProgram()
    {
        Platform.exit();
    }

    //Debug
    public static String getFileInfo(File file, String modificationType, boolean printContent)
    {
        if (modificationType == null)
        {
            modificationType = "[Default operation]";
        }

        StringBuilder info = new StringBuilder();

        info.append("[" + modificationType + "]\n");
        info.append("File name: " + file.getName() + "\n");
        info.append("File path: " + file.getPath() + "\n");
        if(printContent)
        {
            info.append("File content: [ " + getFileContent(file) + " ]");
        }

        return info.toString();
    }

    public static void printDebug(String message)
    {
        System.err.println("DEBUG LOG:");
        System.err.println("{");
        System.err.println(message);
        System.err.println("}");
    }
}