package model.settings;

import autilities.SnideConsts;
import autilities.SnideUtils;
import controller.settings.SettingsController;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsModel
{
    private SettingsController settingsController;

    //SettingsModel elements
    private String settingsFileContent;

    //Todo add options here
    private static boolean doesLogSystemWork;
    private static boolean isOption2;
    private static boolean isOption3;
    private static boolean isOption4;
    //private boolean isOption5;

    public SettingsModel(SettingsController settingsController)
    {
        //Assign
        this.settingsController = settingsController;
        this.settingsFileContent = "";
    }

    //Add booleans here
    public void updateSettingsOptionsFromSettingsFile() //Sets proper booleans from settingsFileContent
    {
        this.settingsFileContent = SnideUtils.getFileContent(new File(SnideConsts.SETTINGS_FILE_PATH));

        if (settingsFileContent != null && this.settingsFileContent.length() > 0 )
        {
            //Todo add options here
            Pattern pattern;
            Matcher matcher;
            String regexForTrueValues =
                    "(doesLogSystemWork\\s*=\\s*true)|" +
                        "(isOption2\\s*=\\s*true)|" +
                        "(isOption3\\s*=\\s*true)|" +
                        "(isOption4\\s*=\\s*true)";
                        //(isOption5\\s*=\\s*true)
            String regexForFalseValues =
                    "(doesLogSystemWork\\s*=\\s*false)|" +
                            "(isOption2\\s*=\\s*false)|" +
                            "(isOption3\\s*=\\s*false)|" +
                            "(isOption4\\s*=\\s*false)";
                            //(isOption5\\s*=\\s*false)

            pattern = Pattern.compile(regexForTrueValues);
            matcher = pattern.matcher(settingsFileContent);
            while (matcher.find())
            {
                if (matcher.group().contains("doesLogSystemWork")) doesLogSystemWork = true;
                if (matcher.group().contains("isOption2")) isOption2 = true;
                if (matcher.group().contains("isOption3")) isOption3 = true;
                if (matcher.group().contains("isOption4")) isOption4 = true;
//                if (matcher.group().contains("isOption5")) isOption5 = true;
            }

            pattern  = Pattern.compile(regexForFalseValues);
            matcher = pattern.matcher(settingsFileContent);
            while (matcher.find())
            {
                if (matcher.group().contains("doesLogSystemWork")) doesLogSystemWork = false;
                if (matcher.group().contains("isOption2")) isOption2 = false;
                if (matcher.group().contains("isOption3")) isOption3 = false;
                if (matcher.group().contains("isOption4")) isOption4 = false;
//                if (matcher.group().contains("isOption5")) isOption5 = false;
            }

            //fixme debug
            SnideUtils.printDebug(this.toString());
        }
    }

    //Add booleans here
    public void createSettingsFile(String pathToSettingsFile) //Creates file with settings
    {
        //Todo add options here
        StringBuilder fileContent = new StringBuilder();

        fileContent.append("doesLogSystemWork = " + doesLogSystemWork + "\n");
        fileContent.append("isOption2 = " + isOption2 + "\n");
        fileContent.append("isOption3 = " + isOption3 + "\n");
        fileContent.append("isOption4 = " + isOption4 + "\n");
//        fileContent.append("isOption5 = " + isOption5 + "\n");

        SnideUtils.createFileWithContent(SnideConsts.SETTINGS_FILE_PATH, fileContent.toString());
    }

    public void updateSettingsFromSettingsWindow()
    {
        //Todo add options here
        this.doesLogSystemWork = this.settingsController.getSettingsWindow().getDoesLogSystemWork();
        this.isOption2 = this.settingsController.getSettingsWindow().getOption2();
        this.isOption3 = this.settingsController.getSettingsWindow().getOption3();
        this.isOption4 = this.settingsController.getSettingsWindow().getOption4();
//        this.isOption5 = this.settingsController.getSettingsWindow().getOption5();
    }

    @Override //fixme delete debug
    public String toString()
    {
        return "SettingsModel{" +
                ", doesLogSystemWork=" + doesLogSystemWork +
                ", isOption2=" + isOption2 +
                ", isOption3=" + isOption3 +
                ", isOption4=" + isOption4 +
                '}';
    }

    //Getters
    public static boolean doesLogSystemWork()
    {
        return doesLogSystemWork;
    }

    public static boolean isOption2()
    {
        return isOption2;
    }

    public static boolean isOption3()
    {
        return isOption3;
    }

    public static boolean isOption4()
    {
        return isOption4;
    }

    //Todo add options here
//    public static  boolean isOption5()
//    {
//        return isOption5;
//    }
}