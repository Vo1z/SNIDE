package model;

import controller.EditorController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EditorModel
{
    private EditorController editorController;
    private ArrayList<FileModel> files;

    public EditorModel(EditorController editorController)
    {
        this.editorController = editorController;
        this.files = new ArrayList<>();
    }

    public void addFile(File inputFile)
    {
        StringBuffer fileContent = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(inputFile);
            int ind;

            while ((ind = fis.read()) != -1)
            {
                fileContent.append((char)ind);
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

        System.out.println("Adding " + inputFile.getName());//fixme debug

        this.files.add(new FileModel(inputFile, fileContent.toString()));
    }

    public void removeFile(int index)
    {
        System.err.println("File " + files.get(index) + " was removed from EditorModel");//fixme debug

        this.files.remove(index);
    }

    //Getters
    public ArrayList<FileModel> getFiles()
    {
        return this.files;
    }


    public int getNumberOfOpenedFiles()
    {
        return  this.files.size();
    }
}
