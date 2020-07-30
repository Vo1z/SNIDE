package model;

import controller.EditorController;
import javafx.scene.control.Tab;

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

    public void saveFile(EditorTab editorTab)
    {
        if (editorTab != null)
            editorTab.saveFile();
    }

    public void saveFile(int index)
    {
        if (index >= 0 && index < editorTabs.size())
            saveFile(this.editorTabs.get(index));
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

    public Tab getTab(int index)
    {
        return this.editorTabs.get(index);
    }
}
