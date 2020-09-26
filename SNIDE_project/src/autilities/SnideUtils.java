package autilities;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import log_lib.LogType;
import log_lib.LogUnit;
import log_lib.LogsStorage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnideUtils
{
    private static boolean isDebugShown = true;

    //IO
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
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(ex, "SnideUtils"));
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
        catch (FileNotFoundException fileNotFoundException)
        {
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(fileNotFoundException, "SnideUtils"));
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioException)
        {
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(ioException, "SnideUtils"));
            ioException.printStackTrace();
        }

        //Finding words
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find())
        {
            words.add(matcher.group());
        }

        return words;
    }

    public static void createFileWithContent(final String path, final String content)
    {
        try
        {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);

            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException ioException)
        {
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(ioException));
            ioException.printStackTrace();
        }
    }

    //File manager
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
        fileChooser.getExtensionFilters().add(SnideConsts.FILES_POOL_EXTENSION);

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
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(e, "SnideUtils"));
            e.printStackTrace();
        }
        catch (IOException e)
        {
            //FIXME LOG
            LogsStorage.addLog(new LogUnit(e, "SnideUtils"));
            e.printStackTrace();
        }

        return fileToSave;
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

    //Windows
    public static void showAlertWindow(String alertMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(alertMessage);
        alert.showAndWait();
    }

    //Threading
    public static void startThread(boolean condition, SnideThreadTask task)
    {
        Thread thread = new Thread(
                () ->
                {
                    while (condition)
                    {
                        Platform.runLater(() -> task.invoke());
                    }
                }
        );

        thread.start();
    }

    public static void startThread(boolean condition, int sleepTimer, SnideThreadTask task)
    {
        Thread thread = new Thread(
                () ->
                {
                    while (condition)
                    {
                        try
                        {
                            Thread.sleep(sleepTimer);
                            Platform.runLater(() -> task.invoke());
                        }
                        catch (InterruptedException e)
                        {
                            //FIXME LOG
                            LogsStorage.addLog(new LogUnit(e, "SnideUtils"));
                            e.printStackTrace();
                        }

                    }
                }
        );

        thread.start();
    }

    public static void stopProgram()
    {
        //Fixme log
        LogsStorage.addLog(new LogUnit(LogType.EVENT, "stopProgram", "SnideUtils"));

        LogsStorage.createLogFiles(SnideConsts.LOG_FILE_PATH);
        Platform.exit();
        System.exit(0);
    }

    //Time
    public static String getCurrentTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        return dtf.format(time);
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
        if (isDebugShown)
        {
            System.err.println("--------------DEBUG LOG--------------");
            System.err.println(message);
        }
    }

    //Configuration
    public static void setIsDebugShown(boolean val)
    {
        isDebugShown = val;
    }

    //Getters
    public static boolean isIsDebugShown()
    {
        return isDebugShown;
    }
}