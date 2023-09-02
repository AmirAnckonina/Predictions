package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDEntity;
import resources.jaxb.schema.generated.PRDProperty;
import simulator.builder.api.interfaces.EntityBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;

import java.util.HashMap;
import java.util.Map;

public class XmlEntityBuilder extends AbstractComponentBuilder implements EntityBuilder {

    private PRDEntity generatedEntityDefinition;

    public XmlEntityBuilder(PRDEntity generatedEntity, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedEntityDefinition = generatedEntity;
    }

    @Override
    public EntityDefinition buildEntity() {
        String entityName = generatedEntityDefinition.getName();
        contextValidator.addEntity(entityName);
        Map<String, AbstractPropertyDefinition> entityProperties = buildEntityProperties();
        return new EntityDefinition(entityName,entityProperties);
    }

    @Override
    public Map<String, AbstractPropertyDefinition> buildEntityProperties() {

        Map<String, AbstractPropertyDefinition> entProperties = new HashMap<>();

        for (PRDProperty genEntityProp : generatedEntityDefinition.getPRDProperties().getPRDProperty()) {

            AbstractPropertyDefinition newProp =
                    new XmlEntityPropertyBuilder(genEntityProp).buildEntityProperty();

            boolean propVerified =
                    contextValidator.validatePrimaryEntityPropertyUniqueness(
                            generatedEntityDefinition.getName(),newProp.getName()
                    );

            if (propVerified && !entProperties.containsKey(newProp.getName())) {
                entProperties.put(newProp.getName(), newProp);
                contextValidator.addEntityProperty(
                        generatedEntityDefinition.getName(), newProp.getName(), newProp.getType());
            } else {
                throw new WorldBuilderException(
                        "Entity property build failed. The following entity property name already exists: " + newProp.getName());
            }
        }
        return entProperties;
    }


}
