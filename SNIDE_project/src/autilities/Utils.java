package autilities;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    private static boolean showDebug = true;

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
            if (fileToSave != null)
            {
                FileWriter fw = new FileWriter(fileToSave);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(fileContent);
                bw.close();

                printDebug("File created: " + getFileInfo(fileToSave, "CREATED", true));//fixme debug
            }
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

    public static String showConfirmDialog(String headerText, String... options)
    {
        //Assign
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        StringBuilder resultStr = new StringBuilder("null");

        //Alert configuration
        alert.getButtonTypes().remove(0, alert.getButtonTypes().size() - 1);
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);

        //Button set up
        Arrays.stream(options)
                .forEach(
                        s ->
                        {
                            ButtonType bt = new ButtonType(s);
                            alert.getButtonTypes().add(bt);
                        }
                );

        //Getting the result
        Optional<ButtonType> resultButtonType = alert.showAndWait();

        alert.getButtonTypes().stream()
                .filter(buttonType -> buttonType == resultButtonType.get())
                .forEach(buttonType -> resultStr.replace(0, resultStr.length(), buttonType.getText()));

        printDebug('\"' + resultStr.toString() + '\"' + "was selected from UTILS");//fixme debug

        return resultStr.toString();
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
        if (showDebug)
        {
            System.err.println("--------------DEBUG LOG--------------");
            System.err.println(message);
            //System.err.println("-------------------------------------");
        }
    }
}