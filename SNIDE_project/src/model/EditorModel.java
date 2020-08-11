package model;

import autilities.Utils;
import controller.EditorController;
import javafx.application.Platform;
import javafx.scene.control.Tab;

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
        startRefresherThread();
    }

    private void startRefresherThread()
    {
        Thread refresher = new Thread(
                () ->
                {
                    while (isRefresherWorking)
                    {
                        try
                        {
                            Platform.runLater(() ->
                            {
                                for (var tab : this.editorTabs)
                                {
                                    tab.updateSaveStatus();
                                }
                            });

                            Thread.sleep(300);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

        refresher.start();
    }

    public void addTab(EditorTab editorTab)
    {
        Utils.printDebug("Adding to MODEL \"" + editorTab.getText() + "\"");//fixme debug
        this.editorTabs.add(editorTab);
    }

    public void removeTab(EditorTab editorTab)
    {
        Utils.printDebug("Removing from MODEL \"" + editorTab.getText() + "\"");//fixme debug
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
