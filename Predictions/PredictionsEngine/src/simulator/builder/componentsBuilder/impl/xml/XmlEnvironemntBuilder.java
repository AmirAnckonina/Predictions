package simulator.builder.componentsBuilder.impl.xml;

import resources.jaxb.schema.generated.PRDEvironment;
import simulator.builder.componentsBuilder.api.EnvironmentBuilder;
import simulator.definition.environment.Environment;

public class XmlEnvironemntBuilder implements EnvironmentBuilder {
    private PRDEvironment generatedEnvironment;

    public void setGeneratedEnvironment(PRDEvironment generatedEnvironment) {
        this.generatedEnvironment = generatedEnvironment;
    }

    @Override
    public Environment buildEnvironment() {
        return null;
    }

    @Override
    public void buildEnvironmentProperty() {

    }
}
