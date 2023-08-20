package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDEvironment;
import simulator.builder.world.api.abstracts.AbstractComponentBuilder;
import simulator.builder.world.api.interfaces.EnvironmentBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.environment.Environment;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEnvironmentBuilder extends AbstractComponentBuilder implements EnvironmentBuilder {
    private PRDEvironment generatedEnvironment;

    public XmlEnvironmentBuilder(PRDEvironment generatedEnvironment, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedEnvironment = generatedEnvironment;
    }

    @Override
    public Environment buildEnvironment() {
        // Need to ensure each property added is a new one and doesn't exist.
        Map<String, AbstractPropertyDefinition> envProperties = buildEnvironmentProperties();
        return new Environment(envProperties);
    }

    @Override
    public Map<String, AbstractPropertyDefinition> buildEnvironmentProperties() {

        Map<String, AbstractPropertyDefinition> envProperties = new HashMap<>();

        for (PRDEnvProperty genEnvProp : generatedEnvironment.getPRDEnvProperty()) {
            AbstractPropertyDefinition newEnvProperty =
                    new XmlEnvironmentPropertyBuilder(genEnvProp).buildEnvironmentProperty();
            String newEnvPropName = newEnvProperty.getName();
            boolean propVerified =
                    contextValidator.validateEnvironmentPropertyUniqueness(newEnvPropName);
            if (propVerified && !envProperties.containsKey(newEnvPropName)) {
                envProperties.put(newEnvPropName, newEnvProperty);
                contextValidator.addEnvironmentProperty(newEnvPropName, newEnvProperty.getType());
            } else {
                throw new WorldBuilderException(
                        "Environment property build failed. The following env property name already exists: " + newEnvProperty.getName());
            }
        }
        return envProperties;
    }
}
