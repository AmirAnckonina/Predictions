package simulator.establishment.impl;

import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.World;
import simulator.establishment.api.SimulationEstablishmentManager;
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
    private World worldDefinition;

    public void setWorldDefinition(World worldDefinition) {
        this.worldDefinition = worldDefinition;
    }

    public World getWorldDefinition() {
        return worldDefinition;
    }

    @Override
    public WorldInstance establishSimulation(World worldDefnition) {

        this.worldDefinition = worldDefnition;

        EnvironmentInstance envInstance = activateEnvironment();
        List<EntityInstance> primaryEntityInstances = createPrimaryEntityInstances();
        List<Rule> rules = this.worldDefinition.getRules();
        Termination termination = this.worldDefinition.getTermination();

        return new WorldInstanceImpl(envInstance, primaryEntityInstances, rules, termination);
    }

    @Override
    public EnvironmentInstance activateEnvironment() {

        Map<String, PropertyInstance> environmentVariables =
                createPropertyInstances(worldDefinition.getEnvironment().getEnvironmentProperties());

        return new EnvironmentInstanceImpl(environmentVariables);
    }

    @Override
    public List<EntityInstance> createPrimaryEntityInstances() {

        // Result :
        List<EntityInstance> primaryEntityInstances = new ArrayList<>();

        for (int index = 0; index < worldDefinition.getPrimaryEntity().getPopulation(); index++) {

            EntityInstance singlePrimaryInstance;

            Map<String, PropertyInstance> entityPropertyInstances =
                    createPropertyInstances(worldDefinition.getPrimaryEntity().getProperties());

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
