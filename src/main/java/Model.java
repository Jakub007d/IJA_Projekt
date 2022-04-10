public class Model {
    private ClassDiagram classDiagram;

    public void parseDiagram()
    {
        this.classDiagram = new Parser().parse();
    }
    public UMLClass getClassFromModel()
    {
        return (UMLClass) classDiagram.findClassifier("Class01");
    }
}
