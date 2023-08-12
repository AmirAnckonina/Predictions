package simulator.definition.environment;

import javafx.beans.property.ListPropertyBase;
import simulator.definition.exception.AmbiguousException;
import simulator.definition.property.api.BasePropertyDefinition;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    String name;

    List<BasePropertyDefinition> environmentProperties;

    public Environment() {
        this.environmentProperties = new ArrayList<>();
    }

    public Environment(List<BasePropertyDefinition> envProperties) {
        this.environmentProperties = envProperties;
    }

    public void addProp(BasePropertyDefinition prop) throws AmbiguousException {
        for(BasePropertyDefinition property : this.environmentProperties){
            if (property.getName() == prop.getName())
                throw new AmbiguousException("Datamember already exist");
        }
        this.environmentProperties.add(prop);
    }
}
