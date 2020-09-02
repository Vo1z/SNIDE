package model.settings;

import autilities.SnideConsts;
import autilities.SnideUtils;
import controller.settings.SettingsController;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;

public class SettingsModel
{
    private SettingsController settingsController;

    //SettingsModel elements
    private String settingsFileContent;
    private boolean doesLogSystemWork;
    private boolean isOption2;
    private boolean isOption3;
    private boolean isOption4;

    public SettingsModel(SettingsController settingsController)
    {
        //Assign
        this.settingsController = settingsController;
        this.settingsFileContent = "";
    }

    //Add booleans here
    public void updateSettingsModel()
    {
        //doesLogSystemWork
        if (this.settingsFileContent.matches("doesLogSystemWork\\s*=\\s*true"))
            this.doesLogSystemWork = true;
        else if(this.settingsFileContent.matches("doesLogSystemWork\\s*=\\s*false"))
            this.doesLogSystemWork = false;
        //option2
        else if (this.settingsFileContent.matches("isOption2\\s*=\\s*true"))
            this.isOption2 = true;
        else if (this.settingsFileContent.matches("isOption2\\s*=\\s*false"))
            this.isOption2 = false;
        //option3
        else if (this.settingsFileContent.matches("isOption3\\s*=\\s*true"))
            this.isOption3 = true;
        else if (this.settingsFileContent.matches("isOption3\\s*=\\s*false"))
            this.isOption3 = false;
        //option4
        else if (this.settingsFileContent.matches("isOption4\\s*=\\s*true"))
            this.isOption4 = true;
        else if (this.settingsFileContent.matches("isOption4\\s*=\\s*false"))
            this.isOption4 = false;
//        else if (this.settingsFileContent.matches("isOption5\\s*=\\s*true"))
//            this.isOption5 = true;
//        else if (this.settingsFileContent.matches("isOption5\\s*=\\s*false"))
//            this.isOption5 = false;
    }

    //Add booleans here
    public void setSettingsFile(String pathToSettingsFile)
    {
        StringBuilder fileContent = new StringBuilder();

        fileContent.append("doesLogSystemWork = " + doesLogSystemWork + "\n");
        fileContent.append("isOption2 = " + isOption2 + "\n");
        fileContent.append("isOption3 = " + isOption3 + "\n");
        fileContent.append("isOption4 = " + isOption4 + "\n");
//        fileContent.append("isOption5 = " + isOption5 + "\n");

        SnideUtils.createFileWithContent(SnideConsts.SETTINGS_FILE_PATH, fileContent.toString());
    }

    public void getSettingsFromFile()
    {
        this.settingsFileContent = SnideUtils.getFileContent(new File(SnideConsts.SETTINGS_FILE_PATH));
        updateSettingsModel();
    }
}