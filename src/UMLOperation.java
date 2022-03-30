
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UMLOperation extends UMLAttribute {
    private java.util.List<UMLAttribute> argumentList = new ArrayList<UMLAttribute>();

    public UMLOperation(String name, UMLClassifier type)
    {
        super(name, type);
    }
    public static UMLOperation create(String name, UMLClassifier type, UMLAttribute... args)
    {
        UMLOperation operation = new UMLOperation(name,type);
        operation.argumentList = List.of(args);
        return operation;
    }
    public boolean addArgument(UMLAttribute arg)
    {
        for (UMLAttribute attr : this.argumentList) {
            if (attr.getName().equals(arg)) {
                return false;
            }
        }
        this.argumentList.add(arg);
        return true;
    }
    public java.util.List<UMLAttribute> getArguments()
    {
        return Collections.unmodifiableList(this.argumentList);
    }
}
