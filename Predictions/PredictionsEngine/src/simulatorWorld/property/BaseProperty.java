package simulatorWorld.property;

public class BaseProperty {

    //should I add an enum for the options (int/float...)?
    private int something;

    public boolean setValue(Object value) {
        return true;
    }

    public Object getValue(){
        return this.something;
    }

}
