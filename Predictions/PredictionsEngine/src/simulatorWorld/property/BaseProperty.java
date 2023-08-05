package simulatorWorld.property;

import javafx.util.Pair;
import simulatorWorld.property.etype.Type;

import javax.print.DocFlavor;

public class BaseProperty {

    private Type.eType type;
    private Object value;
    private Pair<Integer,Integer> range;

    public BaseProperty(Type.eType propertyType){
        this.type = propertyType;

        switch (propertyType){
            case DECIMAL:
                this.value = new Float(0);
                break;
            case STRING:
                this.value = new String("");
                break;
            case BOOLEAN:
                this.value = new Boolean(false);
                break;
        }
    }

    public boolean setValue(Object newValue){
        if(newValue instanceof Float && this.type == Type.eType.DECIMAL){
            this.value = (Float)newValue;
            return true;
        }
        else if(newValue instanceof String && this.type == Type.eType.STRING){
            this.value = (String)newValue;
            return true;
        }
        else if(newValue instanceof Boolean && this.type == Type.eType.BOOLEAN){
            this.value = (Boolean)newValue;
            return true;
        }

        return false;
    }


    public Object getValue(){
        return this.value;
    }

}
