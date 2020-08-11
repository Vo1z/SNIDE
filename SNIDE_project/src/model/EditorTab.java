package model;

import autilities.Utils;
import controller.EditorController;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

import java.io.*;

public class EditorTab extends Tab
{
    private EditorController editorController;
    private File file;
    private String fileContent;

    //Tab components
    private TextArea tabTextArea;

    public EditorTab(File inputFile, EditorController editorController)
    {
        //Assign
        this.file = inputFile;
        this.editorController = editorController;
        this.fileContent = Utils.getFileContent(inputFile);
        this.tabTextArea = new TextArea(fileContent);

        //Tab configuration
        this.setOnClosed(event -> this.editorController.getEditorModel().removeTab(this));
        this.setText(file.getName());
        this.setContent(tabTextArea);
    }

    public EditorTab(EditorController editorController)
    {
        this.editorController = editorController;
        this.fileContent = "";
        this.tabTextArea = new TextArea(fileContent);

        //Tab configuration
        this.setOnClosed(event -> closeTab());
        this.setText("new");
        this.setContent(tabTextArea);
    }

    private void closeTab()
    {
        if (this.tabTextArea.getText() != null && !isSaved())
        {
            //Opens dialog window and gets result from it
            String result = Utils.showConfirmDialog("Do you want to save this file ?", "Yes", "No");

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

                    Utils.printDebug(Utils.getFileInfo(file, "UPDATE", false));//fixme debug
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
                Utils.showAlertWindow("There is no content to save");
            }
            else
            {
                this.file = Utils.getSavedFileFromFileChooser(this.editorController.getMainController().getMainStage(),
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

    public void updateSaveStatus()
    {
        if (!isSaved())
        {
            this.setText(this.file.getName()+'*');
        }
        else
        {
            if (this.file != null)
                this.setText(this.file.getName());
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