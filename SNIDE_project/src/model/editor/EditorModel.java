package model.editor;

import snideUtilities.SnideUtils;
import controller.editor.EditorController;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import view.editor.EditorTab;

import java.util.ArrayList;

public class EditorModel
{
    private EditorController editorController;
    private ArrayList<EditorTab> editorTabs;

    //Model components
    private boolean isRefresherWorking;

    public EditorModel(EditorController editorController)
    {
        this.editorController = editorController;
        this.editorTabs = new ArrayList<>();
        this.isRefresherWorking = true;
        startRefreshingThread();
    }

    private void startRefreshingThread()
    {
        SnideUtils.startThread(this.isRefresherWorking, 300,
                () -> Platform.runLater(() ->
                        {
                            for (var tab : this.editorTabs)
                            {
                                tab.updateSaveStatus();
                            }
                        }
                )
        );
    }

    public void addTab(EditorTab editorTab)
    {
        SnideUtils.printDebug("Adding to MODEL \"" + editorTab.getText() + "\"");//fixme debug
        this.editorTabs.add(editorTab);
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

    public void removeTab(EditorTab editorTab)
    {
        SnideUtils.printDebug("Removing from MODEL \"" + editorTab.getText() + "\"");//fixme debug
        this.editorTabs.remove(editorTab);
    }

    public void removeTab(int index)
    {
        this.editorTabs.remove(index);
    }

    public void stopRefresherThread()
    {
        this.isRefresherWorking = false;
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
