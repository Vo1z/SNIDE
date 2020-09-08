package controller.settings;

import controller.MainController;
import model.settings.SettingsModel;
import view.settings.SettingsWindow;

public class SettingsController
{
    private MainController mainController;
    private SettingsWindow settingsWindow;
    private SettingsModel settingsModel;

    public SettingsController(MainController mainController, SettingsWindow settingsWindow, SettingsModel settingsModel)
    {
        this.mainController = mainController;
        this.settingsWindow = settingsWindow;
        this.settingsModel = settingsModel;
    }

    public void saveSettings()
    {
    }
}
