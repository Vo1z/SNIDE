package controller.editor;

import controller.MainController;
import model.editor.EditorModel;
import view.editor.EditorTab;
import view.editor.EditorWindow;
import view.launch.LaunchWindow;

import java.io.File;

public class EditorController
{
    private MainController mainController;
    private LaunchWindow launchWindow;
    private EditorWindow editorWindow;
    private EditorModel editorModel;


    public EditorController(MainController mainController)
    {
        this.mainController = mainController;
        this.launchWindow = new LaunchWindow(this);
        this.editorWindow = new EditorWindow(this);
        this.editorModel = new EditorModel(this);
    }

    public void createNewFile()
    {
        this.editorModel.addTab(new EditorTab(this));
        this.editorWindow.updateTabs();
    }

    public void addFileToEditor(File aFile)
    {
        this.editorModel.addTab(new EditorTab(aFile, this));
        this.editorWindow.updateTabs();
    }

    public void removeFileFromEditor(EditorTab editorTab)
    {
        this.editorModel.removeTab(editorTab);
        this.editorWindow.updateTabs();
    }

    //Getters
    public LaunchWindow getLaunchWindow()
    {
        return this.launchWindow;
    }

    public EditorModel getEditorModel()
    {
        return editorModel;
    }

    public EditorWindow getEditorWindow()
    {
        return editorWindow;
    }

    public MainController getMainController()
    {
        return mainController;
    }
}
