
public class Element extends Object{
    private String name;
    public Element(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void rename(String newName)
    {
        this.name = newName;
    }
}
