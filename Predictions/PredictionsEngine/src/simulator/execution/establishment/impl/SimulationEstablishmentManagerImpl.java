package simulator.execution.establishment.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.WorldDefinition;
import simulator.execution.establishment.api.SimulationEstablishmentManager;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.impl.EntityInstanceImpl;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.environment.impl.EnvironmentInstanceImpl;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationEstablishmentManagerImpl implements SimulationEstablishmentManager {
    private WorldDefinition worldDefinition;

    public void setWorldDefinition(WorldDefinition worldDefinition) {
        this.worldDefinition = worldDefinition;
    }

    public WorldDefinition getWorldDefinition() {
        return worldDefinition;
    }

    @Override
    public WorldInstance establishSimulation(WorldDefinition worldDefinition) {

        this.worldDefinition = worldDefinition;
        EnvironmentInstance envInstance = activateEnvironment();
        Map<String, List<EntityInstance>> entitiesInstances = createEntitiesInstances();
        List<Rule> rules = this.worldDefinition.getRules();
        Termination termination = this.worldDefinition.getTermination();

        return new WorldInstanceImpl(envInstance, entitiesInstances, rules, termination);
    }

    @Override
    public EnvironmentInstance activateEnvironment() {

        Map<String, PropertyInstance> environmentVariables =
                createPropertyInstances(worldDefinition.getEnvironment().getEnvironmentProperties());

        return new EnvironmentInstanceImpl(environmentVariables);
    }

    @Override
    public Map<String, List<EntityInstance>> createEntitiesInstances() {

        Map<String , List<EntityInstance>> entitiesInstances =   new HashMap<>();
        Map<String, EntityDefinition> entitiesDefinitions = this.worldDefinition.getEntities();
        entitiesDefinitions
                .forEach(
                        (entName, entDef) ->
                                entitiesInstances.put(
                                        entName, createSingleEntityInstances(entDef)
                                )
                );

        return entitiesInstances;
    }

    @Override
    public List<EntityInstance> createSingleEntityInstances(EntityDefinition entityDefinition) {

        // Result :
        List<EntityInstance> primaryEntityInstances = new ArrayList<>();

        for (int index = 0; index < entityDefinition.getPopulation(); index++) {

            EntityInstance singlePrimaryInstance;

            Map<String, PropertyInstance> entityPropertyInstances =
                    createPropertyInstances(entityDefinition.getProperties());

            singlePrimaryInstance = new EntityInstanceImpl(index + 1, entityPropertyInstances);

            // Finally after we build singleEntityInstance with it properties, we add it to the list result.
            primaryEntityInstances.add(singlePrimaryInstance);
        }

        return primaryEntityInstances;
    }

    @Override
    public Map<String, PropertyInstance> createPropertyInstances(
            Map<String, AbstractPropertyDefinition> propertyDefinitions) {

        Map<String, PropertyInstance> propertyInstanceMap = new HashMap<>();

        for (Map.Entry<String, AbstractPropertyDefinition> entry :
                propertyDefinitions.entrySet()) {

            /// Note - we should validate key (bane) == value.getName(); in each propery Entry <K,V>

            String propName = entry.getKey();
            AbstractPropertyDefinition propDefinition = entry.getValue();

            PropertyInstance propertyInstance =
                    new PropertyInstanceImpl(propDefinition, propDefinition.generateValue());

            propertyInstanceMap.put(propName, propertyInstance);
        }

        return  propertyInstanceMap;
    }

    @Override
    public Map<String, String> getEstablishedEnvironmentInfo(WorldInstance establishedWorldInstance) {
        Map<String, String> envInfo = new HashMap<>();

        Map<String, PropertyInstance> envPropInstances =
                establishedWorldInstance.getEnvironmentInstance().getAllEnvironmentPropertiesInstances();

        for (Map.Entry<String, PropertyInstance> envPropInstance : envPropInstances.entrySet()) {
            envInfo.put(
                    envPropInstance.getKey(),
                    ePropertyType.STRING.convert(envPropInstance.getValue().getValue().toString()));
        }

        return envInfo;
    }
}
