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

    //Setters
    public void setEditorWindow(EditorWindow editorWindow)
    {
        this.editorWindow = editorWindow;
    }

    public void setEditorModel(EditorModel editorModel)
    {
        this.editorModel = editorModel;
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
