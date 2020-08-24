package autilities;

import controller.MainController;
import javafx.application.Platform;
import model.EditorModel;
import model.EditorTab;

public class ThreadManager
{
    //References
    private MainController mainController;

    //ThreadManager components
    private boolean isEditorModelRefresherWorking;
    private boolean isEditorTabRefresherWorking;

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
                    }
                }
        );
    }

    //EditorTab s thread
    private void configureEditorTabRefresher()
    {
        EditorTab editorTab = this.mainController
                .getEditorController()
                .getEditorWindow()
                .getSelectedEditorTab();

        //TODO implement refresher inside EditorTab
//        SnideUtils.startThread(this.isEditorTabRefresherWorking,
//                () ->
//                {
//                    editorTab = this.mainController
//                            .getEditorController()
//                            .getEditorWindow()
//                            .getSelectedEditorTab();
//                }
//        );
    }

    public void stopEditorModelRefresher()
    {
        this.isEditorModelRefresherWorking = false;
    }
}
