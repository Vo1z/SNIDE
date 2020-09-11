package controller.settings;

import autilities.SnideConsts;
import controller.MainController;
import model.settings.SettingsModel;
import view.settings.SettingsWindow;

public class SettingsController
{
    private MainController mainController;
    private SettingsModel settingsModel;
    private SettingsWindow settingsWindow;

    public SettingsController(MainController mainController)
    {
        this.mainController = mainController;
        this.settingsModel = new SettingsModel(this);
        this.settingsWindow = new SettingsWindow(this);

        this.settingsModel.updateSettingsOptionsFromSettingsFile();
        this.settingsWindow.updateOptionsFromModel();
    }

    public void saveSettings()
    {
        //TODO rework
        this.settingsModel.updateSettingsFromSettingsWindow();
        this.settingsModel.createSettingsFile(SnideConsts.SETTINGS_FILE_PATH);
        this.settingsWindow.updateOptionsFromModel();
    }

    //Getters
    public SettingsWindow getSettingsWindow()
    {
        return this.settingsWindow;
    }

    public SettingsModel getSettingsModel()
    {
        return this.settingsModel;
    }

    public MainController getMainController()
    {
        return this.mainController;
    }
}
