package model;

import controller.EditorController;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        this.setOnClosed(event -> editorController.removeFileFromEditor(this));
        this.setText(file.getName());
        this.setContent(tabTextArea);
    }

    private String getContentFromFile(File file)
    {
        StringBuffer fileContentBuffer = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(file);
            int charId;

            while((charId = fis.read()) != -1)
            {
                fileContentBuffer.append((char)charId);
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

    //Getters
    private String getInitialFileContent()
    {
        return this.initialFileContent;
    }

    private File getFile()
    {
        return this.file;
    }

    private String getTextAreaContent()
    {
        return tabTextArea.getText();
    }
}
