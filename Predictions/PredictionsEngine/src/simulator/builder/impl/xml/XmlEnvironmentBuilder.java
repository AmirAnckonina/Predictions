package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDEnvironment;
import simulator.builder.api.interfaces.EnvironmentBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.environment.EnvironmentDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEnvironmentBuilder extends AbstractComponentBuilder implements EnvironmentBuilder {
    private PRDEnvironment generatedEnvironment;

    public XmlEnvironmentBuilder(PRDEnvironment generatedEnvironment, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedEnvironment = generatedEnvironment;
    }

    @Override
    public EnvironmentDefinition buildEnvironment() {
        // Need to ensure each property added is a new one and doesn't exist.
        Map<String, AbstractPropertyDefinition> envProperties = buildEnvironmentProperties();
        return new EnvironmentDefinition(envProperties);
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
