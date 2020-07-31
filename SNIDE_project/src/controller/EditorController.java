package controller;

import model.EditorModel;
import model.EditorTab;
import view.EditorWindow;

import java.io.File;

public class EditorController
{
    private MainController mainController;
    private EditorWindow editorWindow;
    private EditorModel editorModel;

    public EditorController(MainController mainController, EditorWindow editorWindow, EditorModel editorModel)
    {
        this.mainController = mainController;
        this.editorWindow = editorWindow;
        this.editorModel = editorModel;
    }

    public EditorController(MainController mainController)
    {
        this.mainController = mainController;
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
