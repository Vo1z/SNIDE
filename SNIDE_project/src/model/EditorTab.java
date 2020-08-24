package model;

import autilities.SnideUtils;
import controller.EditorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorTab extends Tab
{
    private EditorController editorController;
    private File file;
    private String fileContent;

    //Tab components
    private TextArea tabTextArea;
    private ListView<String> linesCounterListView;
    private ObservableList<String> linesCounterObservableList;

    public EditorTab(File inputFile, EditorController editorController)
    {
        //Assign
        this.file = inputFile;
        this.editorController = editorController;
        this.fileContent = SnideUtils.getFileContent(inputFile);
        this.tabTextArea = new TextArea(fileContent);

        //Tab configuration
        this.setOnClosed(event -> this.editorController.getEditorModel().removeTab(this));
        this.setText(file.getName());
        this.setContent(createRootPane());//todo
    }

    public EditorTab(EditorController editorController)
    {
        this.editorController = editorController;
        this.fileContent = "";
        this.tabTextArea = new TextArea(fileContent);

        //Tab configuration
        this.setOnClosed(event -> closeTab());
        this.setText("new");
        this.setContent(createRootPane());//todo
    }

    private Pane createRootPane()
    {
        GridPane root = new GridPane();

        //root.add(this.linesCounterListView, 0, 0, 1, 10);
        root.add(tabTextArea, 1, 0, 10 , 10);

        return root;

    }

    private void startRefreshingThread()//Todo implement thread inside ThreadManager
    {
        //Assign
        ObservableList<String> lineNumbers = FXCollections.observableArrayList();
        ListView<String> lineNumbersListView = new ListView<>();

        for(int i = 1; i <= this.tabTextArea.getText().split("\n").length; i++)
        {
            lineNumbers.add(i + "");
        }

        //ListView configuration
        lineNumbersListView.setItems(lineNumbers);
        lineNumbersListView.snapSpaceY(0.5f);
    }

    public void saveFile()
    {
        if (this.file != null)
        {
            if (!isSaved())
            {
                try
                {
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(this.tabTextArea.getText());
                    bw.close();

                    this.fileContent = this.tabTextArea.getText();

                    SnideUtils.printDebug(SnideUtils.getFileInfo(file, "UPDATE", false));//fixme debug
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            if (this.tabTextArea.getText().length() <= 0)
            {
                SnideUtils.showAlertWindow("There is no content to save");
            }
            else
            {
                this.file = SnideUtils.getSavedFileFromFileChooser(this.editorController.getMainController().getMainStage(),
                        this.tabTextArea.getText()); //Creates new file and inputs content;
            }
        }

        //Updates tab title due to file name
        if (file != null)
        {
            this.setText(file.getName());
        }
        else
        {
            this.setText("new");
        }
    }

    public void closeTab()
    {
        if (this.tabTextArea.getText() != null && !isSaved())
        {
            //Opens dialog window and gets result from it
            String result = SnideUtils.showConfirmDialog("Do you want to save this file ?", "Yes", "No");

            //Reviews result
            switch (result)
            {
                case "Yes":
                    saveFile();
                    break;
                case "No":
                    this.editorController.removeFileFromEditor(this);
                    break;
                default:
                    this.editorController.getEditorWindow().updateTabs();
            }
        }
        else
        {
            this.editorController.removeFileFromEditor(this);
        }
    }

    public void updateSaveStatus()
    {
        if (!isSaved())
        {
            if (this.file != null)
                this.setText(this.file.getName() + '*');
            else if (this.tabTextArea.getText() != null)
                this.setText("new*");
        }
        else
        {
            if (this.file != null)
                this.setText(this.file.getName());
            else
                this.setText("new");
        }
    }

    //Getters
    public String getFileContent()
    {
        return this.fileContent;
    }

    public File getFile()
    {
        return this.file;
    }

    public String getTextAreaContent()
    {
        return this.tabTextArea.getText();
    }

    public boolean isSaved()
    {
        if (this.tabTextArea.getText().equals(fileContent))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}