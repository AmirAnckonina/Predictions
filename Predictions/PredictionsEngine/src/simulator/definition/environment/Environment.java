package simulator.definition.environment;

import javafx.beans.property.ListPropertyBase;
import simulator.definition.property.api.BasePropertyDefinition;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    List<BasePropertyDefinition> environmentProperties;

    public Environment() {
        this.environmentProperties = new ArrayList<>();
    }

    public Environment(List<BasePropertyDefinition> envProperties) {
        this.environmentProperties = envProperties;
    }

    public void addProp(BasePropertyDefinition prop) {

    }
}
