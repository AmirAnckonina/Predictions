package simulator.definition.environment;

import simulator.definition.property.api.BasePropertyDefinition;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    List<BasePropertyDefinition> environmentProperties;
    public Environment() {
        environmentProperties = new ArrayList<>();
    }

    public void addProp(BasePropertyDefinition prop) {

    }
}
