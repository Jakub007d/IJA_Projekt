public class UMLRelationship extends UMLClassifier {
    private String lCardinality;
    private String rCardinality;
    private UMLClass leftClass;
    private UMLClass rightClass;
    public UMLRelationship(String name,String lCardinality, String rCardinality,UMLClass leftClass,UMLClass rightClass){
        super(name);
        this.lCardinality=lCardinality;
        this.leftClass=leftClass;
        this.rCardinality=rCardinality;
        this.rightClass=rightClass;


    }
    public String getLeftCardinality(){
        return this.lCardinality;

    }
    public String getRightCardinality()
    {
        return this.rCardinality;
    }
    public UMLClass getRightClass(){
        return this.rightClass;
    }
    public UMLClass getLeftClass(){
        return this.leftClass;
    }

}
