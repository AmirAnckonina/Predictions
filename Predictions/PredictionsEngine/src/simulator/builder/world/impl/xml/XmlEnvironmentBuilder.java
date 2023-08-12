package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDEvironment;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.EnvironmentBuilder;
import simulator.definition.environment.Environment;
import simulator.definition.property.api.BasePropertyDefinition;

import java.util.ArrayList;
import java.util.List;

public class XmlEnvironmentBuilder extends AbstractFileComponentBuilder<Environment> implements EnvironmentBuilder {
    private PRDEvironment generatedEnvironment;

    public XmlEnvironmentBuilder(PRDEvironment generatedEnvironment) {
        super();
        this.generatedEnvironment = generatedEnvironment;
    }

    public XmlEnvironmentBuilder() {
        super();
    }

    public void setGeneratedEnvironment(PRDEvironment generatedEnvironment) {
        this.generatedEnvironment = generatedEnvironment;
    }

    @Override
    public Environment buildEnvironment() {

        Environment environment;

        List<BasePropertyDefinition> envProperties = buildEnvironmentProperties();

        return new Environment(envProperties);
    }

    @Override
    public List<BasePropertyDefinition> buildEnvironmentProperties() {
        List<BasePropertyDefinition> envProperties = new ArrayList<>();

        List<PRDEnvProperty> generatedEnvProperties = generatedEnvironment.getPRDEnvProperty();
        for (PRDEnvProperty generatedEnvProp : generatedEnvProperties) {
            XmlPropertyBuilder propBuilder = new XmlPropertyBuilder(generatedEnvProp);
            BasePropertyDefinition newProperty = propBuilder.buildEnvironmentProperty();
            envProperties.add(newProperty);
        }

        return envProperties;
    }
}
