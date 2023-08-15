package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDEvironment;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.EnvironmentBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.environment.Environment;
import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEnvironmentBuilder implements EnvironmentBuilder {
    private PRDEvironment generatedEnvironment;

    public XmlEnvironmentBuilder(PRDEvironment generatedEnvironment) {
        this.generatedEnvironment = generatedEnvironment;
    }

    @Override
    public Environment buildEnvironment() {
        // Need to ensure each property added is a new one and doesn't exist.
        Map<String, AbstractPropertyDefinition> envProperties = buildEnvironmentProperties();
        return new Environment(envProperties);
    }


    public Map<String, AbstractPropertyDefinition> buildEnvironmentProperties() {

        Map<String, AbstractPropertyDefinition> envProperties = new HashMap<>();

        for (PRDEnvProperty genEnvProp : generatedEnvironment.getPRDEnvProperty()) {
            AbstractPropertyDefinition newEnvProperty = new XmlPropertyBuilder(genEnvProp).buildEnvironmentProperty();
            if (!envProperties.containsKey(newEnvProperty.getName())) {
                envProperties.put(newEnvProperty.getName(), newEnvProperty);
            } else {
                throw new WorldBuilderException(
                        "Environment property build failed. The following env property name already exists: " + newEnvProperty.getName());
            }
        }
        return envProperties;
    }
}
