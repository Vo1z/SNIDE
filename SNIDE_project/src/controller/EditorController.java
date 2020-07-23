package controller;

import model.EditorModel;
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

    public void addFileToEditor(File aFile)
    {
        editorModel.addFile(aFile);
        editorWindow.addTab(aFile, editorModel.getFiles().get(editorModel.getNumberOfOpenedFiles() - 1).getContent());
    }

    public void removeFileFromEditor(int index)
    {
        this.editorWindow.removeTab(index);
        this.editorModel.removeFile(index);
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
}
