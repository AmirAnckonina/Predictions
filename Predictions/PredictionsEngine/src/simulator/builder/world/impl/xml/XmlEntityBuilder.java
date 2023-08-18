package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDEntity;
import resources.jaxb.schema.generated.PRDProperty;
import simulator.builder.world.api.abstracts.AbstractComponentBuilder;
import simulator.builder.world.api.interfaces.EntityBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.entity.Entity;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEntityBuilder extends AbstractComponentBuilder implements EntityBuilder {

    private PRDEntity generatedEntity;

    public XmlEntityBuilder(PRDEntity generatedEntity, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedEntity = generatedEntity;
    }


    @Override
    public Entity buildEntity() {
        String entityName = generatedEntity.getName();
        int population = generatedEntity.getPRDPopulation();
        contextValidator.addPrimaryEntity(entityName);
        Map<String, AbstractPropertyDefinition> entityProperties = buildEntityProperties();
        return new Entity(entityName,population, entityProperties);
    }

    @Override
    public Map<String, AbstractPropertyDefinition> buildEntityProperties() {

        Map<String, AbstractPropertyDefinition> envProperties = new HashMap<>();

        for (PRDProperty genEntityProp : generatedEntity.getPRDProperties().getPRDProperty()) {

            AbstractPropertyDefinition newProp = new XmlEntityPropertyBuilder(genEntityProp).buildEntityProperty();
            boolean propVerified =
                    contextValidator.validatePrimaryEntityPropertyUniqueness(newProp.getName());

            if (propVerified && !envProperties.containsKey(newProp.getName())) {
                envProperties.put(newProp.getName(), newProp);
                contextValidator.addEntityProperty(
                        generatedEntity.getName(), newProp.getName(), newProp.getType());
            } else {
                throw new WorldBuilderException(
                        "Entity property build failed. The following entity property name already exists: " + newProp.getName());
            }
        }
        return envProperties;
    }


}
