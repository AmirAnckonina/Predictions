package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEntity;
import resources.jaxb.schema.generated.PRDProperty;
import simulator.builder.world.api.AbstractFileComponentBuilder;
import simulator.builder.world.api.EntityBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.entity.Entity;
import simulator.definition.property.api.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEntityBuilder implements EntityBuilder {

    private PRDEntity generatedEntity;

    public XmlEntityBuilder(PRDEntity generatedEntity) {
        this.generatedEntity = generatedEntity;
    }


    public XmlEntityBuilder() {
        super();
    }

    @Override
    public Entity buildEntity() {
        String name = generatedEntity.getName();
        Map<String, AbstractPropertyDefinition> entityProperties = buildEntityProperties();
        return new Entity(name, entityProperties);
    }

    @Override
    public Map<String, AbstractPropertyDefinition> buildEntityProperties() {
        Map<String, AbstractPropertyDefinition> envProperties = new HashMap<>();

        for (PRDProperty genEntityProp : generatedEntity.getPRDProperties().getPRDProperty()) {
            AbstractPropertyDefinition newEntityProperty = new XmlPropertyBuilder(genEntityProp).buildEntityProperty();
            if (!envProperties.containsKey(newEntityProperty.getName())) {
                envProperties.put(newEntityProperty.getName(), newEntityProperty);
            } else {
                throw new WorldBuilderException(
                        "Entity property build failed. The following entity property name already exists: " + newEntityProperty.getName());
            }
        }
        return envProperties;
    }


}
