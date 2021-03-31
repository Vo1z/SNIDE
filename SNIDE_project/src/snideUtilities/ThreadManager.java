package snideUtilities;

import controller.MainController;
import model.editor.EditorModel;

public class ThreadManager
{
    //References
    private MainController mainController;

    //ThreadManager components
    private boolean isEditorModelRefresherWorking;

    public ThreadManager(MainController mainController)
    {
        //Assign
        this.mainController = mainController;
        this.isEditorModelRefresherWorking = true;

        //ThreadConfiguration
        configureEditorModelRefresher();
    }

    //EditorModel thread
    private void configureEditorModelRefresher()
    {
        EditorModel editorModel = this.mainController.getEditorController().getEditorModel();

        SnideUtils.startThread(this.isEditorModelRefresherWorking, 300,
                () ->
                {
                    for (var tab : editorModel.getEditorTabs())
                    {
                        tab.updateSaveStatus();
                        tab.updateLabels();
                    }
                }
        );
    }

    public void stopEditorModelRefresher()
    {
        this.isEditorModelRefresherWorking = false;
    }
}
