package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDEnvProperty;
import resources.jaxb.schema.generated.PRDEvironment;
import simulator.builder.api.AbstractFileComponentBuilder;
import simulator.builder.api.EnvironmentBuilder;
import simulator.definition.environment.Environment;

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

        Environment environment = new Environment();
        List<PRDEnvProperty> generatedEnvProperties = generatedEnvironment.getPRDEnvProperty();
        for (PRDEnvProperty generatedEnvProp : generatedEnvProperties) {

        }
        MapEnvironment(generatedEnvironment, environment);
        return new Environment();
    }

    @Override
    public void buildEnvironmentProperty() {

    }
}
