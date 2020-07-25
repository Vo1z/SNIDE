package model;

import controller.EditorController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditorModel
{
    private EditorController editorController;
    private ArrayList<EditorTab> editorTabs;

    public EditorModel(EditorController editorController)
    {
        this.editorController = editorController;
        this.editorTabs = new ArrayList<>();
    }

    public void addTab(EditorTab editorTab)
    {
        this.editorTabs.add(editorTab);
    }

    public void removeTab(EditorTab editorTab)
    {
        System.err.println("Removing from MODEL " + editorTab.getText());//fixme debug
        this.editorTabs.remove(editorTab);
    }

    public void removeTab(int index)
    {
        this.editorTabs.remove(index);
    }

    //Getters
    public ArrayList<EditorTab> getEditorTabs()
    {
        return this.editorTabs;
    }

    public int getNumberOfTabs()
    {
        return this.editorTabs.size();
    }
}
