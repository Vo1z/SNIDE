package model;

import controller.MainController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditorModel
{
    private MainController mainController;

    private ArrayList<File> keys;
    private HashMap<File, String> openedFiles;

    public EditorModel(MainController mainController)
    {
        this.mainController = mainController;

        this.keys = new ArrayList<>();
        this.openedFiles = new HashMap<>();
    }

    public void addFile(File file)
    {
        StringBuffer fileContent = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(file);
            int charIndex;

            while ((charIndex = fis.read()) != -1)
            {
                fileContent.append((char)charIndex);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

        this.keys.add(file);
        this.openedFiles.put(file, fileContent.toString());
    }

    public void removeFile(int fromIndex, int toIndex)
    {
        if (fromIndex == toIndex)
        {
            System.err.println("Deleting file from model " + keys.get(fromIndex).getName());

            this.openedFiles.remove(keys.get(fromIndex));
        }
        else
        {
            for (int i = fromIndex; i < toIndex; i++)
            {
                //fixme debug
                System.err.println("Deleting file from model " + keys.get(i).getName());

                this.openedFiles.remove(keys.get(i));
            }
        }
    }

    //Getters
    public HashMap<File, String> getOpenedFiles()
    {
        return this.openedFiles;
    }
}
