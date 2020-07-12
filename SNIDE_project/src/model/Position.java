package model;

public class Position
{
    private int start;
    private int end;

    public Position(final int start, final int end)
    {
        this.start = start;
        this.end = end;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }
}
