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

    public void saveFile()
    {
        if (!isSaved())
        {
            try
            {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bf = new BufferedWriter(fw);

                bf.write(this.tabTextArea.getText());

                Utils.printDebug(Utils.getFileInfo(file, "UPDATE"));//fixme debug
                bf.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
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
