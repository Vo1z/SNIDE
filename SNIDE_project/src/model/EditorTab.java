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
    private String initialFileContent;

    //Tab components
    private TextArea tabTextArea;

    public EditorTab(File inputFile, EditorController editorController)
    {
        //Assign
        this.file = inputFile;
        this.editorController = editorController;
        this.initialFileContent = getContentFromFile(file);
        this.tabTextArea = new TextArea(initialFileContent);

        //Tab configuration
        this.setOnClosed(event -> this.editorController.getEditorModel().removeTab(this));
        this.setText(file.getName());
        this.setContent(tabTextArea);
    }

    public EditorTab(EditorController editorController)
    {
        this.editorController = editorController;
        this.initialFileContent = "";
        this.tabTextArea = new TextArea(initialFileContent);

        //Tab configuration
        this.setOnClosed(event -> closeTab());
        this.setText("new");
        this.setContent(tabTextArea);
    }

    private String getContentFromFile(File file)
    {
        StringBuffer fileContentBuffer = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(file);
            int charId;

            while ((charId = fis.read()) != -1)
            {
                fileContentBuffer.append((char) charId);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return fileContentBuffer.toString();
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

    //Getters
    public String getInitialFileContent()
    {
        return this.initialFileContent;
    }

    public File getFile()
    {
        return this.file;
    }

    public String getTextAreaContent()
    {
        return tabTextArea.getText();
    }

    public boolean isSaved()
    {
        if (this.tabTextArea.getText().equals(initialFileContent))
            return true;
        else
            return false;
    }
}
