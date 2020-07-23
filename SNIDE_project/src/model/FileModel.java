package model;

import java.io.File;

public class FileModel
{
    private File file;
    private String content;

    public FileModel(File file, String content)
    {
        this.file = file;
        this.content = content;
    }

    public File getFile()
    {
        return file;
    }

    public String getContent()
    {
        return content;
    }
}
