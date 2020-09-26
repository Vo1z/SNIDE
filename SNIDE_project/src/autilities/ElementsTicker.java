package autilities;

import java.util.Collection;

public class ElementsTicker<T>
{
    private int index;
    private Object elements[];

    public ElementsTicker(int numberOfElements)
    {
        this.elements = new Object[numberOfElements];
        this.index = 0;
    }

    public ElementsTicker(T[] elements)
    {
        this.elements = elements;
        this.index = 0;
    }

    public ElementsTicker(Collection<T> elements)
    {
        this.elements = elements.toArray();
        this.index = 0;
    }

    public void push(T element)
    {
        for (int i = 0; i < elements.length; i++)
        {
            if (i + 1 < elements.length)
            {
                elements[i] = elements[i + 1];
            }
        }

        elements[elements.length - 1] = element;
    }
    //Getters
    public Object[] getElements()
    {
        return this.elements;
    }
}
