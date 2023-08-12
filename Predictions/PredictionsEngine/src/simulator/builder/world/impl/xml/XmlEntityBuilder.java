package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEntity;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.EntityBuilder;
import simulator.definition.entity.Entity;

public class XmlEntityBuilder extends AbstractFileComponentBuilder<Entity> implements EntityBuilder {

    private PRDEntity generatedEntity;

    public XmlEntityBuilder(PRDEntity generatedEntity) {
        super();
        this.generatedEntity = generatedEntity;
    }

    public XmlEntityBuilder() {
        super();
    }

    @Override
    public Entity buildEntity() {

        return null;
    }

    @Override
    public void buildEntityProperty() {
        // Invesitgate the PRDValue object and decide which params to pass to the PropertyBuilder

    }

    public void setGeneratedEntity(PRDEntity generatedEntity) {
        this.generatedEntity = generatedEntity;
    }
}
