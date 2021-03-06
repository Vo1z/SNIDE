package log_lib;

import model.settings.SettingsModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class LogsStorage
{
    private static LinkedList<LogUnit> logs = new LinkedList<>();

    public static void addLog(LogUnit logUnit)
    {
        if (SettingsModel.doesLogSystemWork())
            logs.add(logUnit);
    }

    public static void removeLog(LogUnit logUnit)
    {
        if (SettingsModel.doesLogSystemWork())
            logs.remove(logUnit);
    }

    public static void removeLog(int index)
    {
        if (SettingsModel.doesLogSystemWork())
            logs.remove(index);
    }

    public static void createLogFiles(String path)
    {
        if (SettingsModel.doesLogSystemWork())
        {
            try
            {
                File fileWithLogs = new File(path);

                if (fileWithLogs.createNewFile()) //If file created
                {
                    FileWriter fileWriter = new FileWriter(fileWithLogs);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write(getAllLogsInString());

                    bufferedWriter.close();
                    fileWriter.close();
                }
                else
                {
                    fileWithLogs.delete();
                    createLogFiles(path);
                }
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }

    //Getters
    public static int getLogsNumber()
    {
        return logs.size();
    }

    public static String getAllLogsInString()
    {
        StringBuilder logsInfo = new StringBuilder();
        logs.stream()
                .forEach(log -> logsInfo.append(log));

        return logsInfo.toString();
    }
}