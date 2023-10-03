package simulator.establishment.manager.impl;

import dto.establishment.EstablishedEnvironmentInfoDto;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.instance.spaceGrid.impl.SpaceGridInstanceWrapperImpl;
import simulator.definition.entity.EntityDefinition;
import simulator.definition.property.api.abstracts.AbstractPropertyDefinition;
import enums.PropertyType;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;
import simulator.definition.world.WorldDefinition;
import simulator.establishment.exception.SimulationEstablishmentException;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.entity.impl.EntityInstanceImpl;
import simulator.execution.instance.environment.api.EnvironmentInstance;
import simulator.execution.instance.environment.impl.EnvironmentInstanceImpl;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.execution.instance.property.impl.PropertyInstanceImpl;
import simulator.execution.instance.world.api.WorldInstance;
import simulator.execution.instance.world.impl.WorldInstanceImpl;
import simulator.execution.instance.movement.manager.api.MovementManager;
import simulator.execution.instance.movement.manager.impl.MovementManagerImpl;

import java.util.*;

public class EstablishmentManagerImpl implements EstablishmentManager {
    private static final int DEFAULT_STARTING_TICK = 0;
    private WorldDefinition worldDefinition;
    private WorldInstance worldInstance;

    public void setWorldDefinition(WorldDefinition worldDefinition) {
        this.worldDefinition = worldDefinition;
    }

    public WorldDefinition getWorldDefinition() {
        return worldDefinition;
    }
    @Override
    public void establishSimulation(WorldDefinition worldDefinition) {
        try {
                this.worldDefinition = worldDefinition;
                Map<String, EntityDefinition> entityDefinitionMap = createEntityDefinitionMap();
                EnvironmentInstance envInstance = establishEnvironment();
                Map<String, List<EntityInstance>> entitiesInstances = createAllEntitiesInstances();
                // please validate the spaceGridInstance
                SpaceGridInstanceWrapper spaceGrid =
                        new SpaceGridInstanceWrapperImpl(
                                worldDefinition.getSpaceGridDefinition().getRows(),
                                worldDefinition.getSpaceGridDefinition().getColumns());
                List<Rule> rules = this.worldDefinition.getRules();
                Termination termination = this.worldDefinition.getTermination();
                MovementManager movementManager = new MovementManagerImpl();
                movementManager.placeEntitiesRandomizeOnSpaceGrid(entitiesInstances, spaceGrid);
                this.worldInstance = new WorldInstanceImpl(entityDefinitionMap, envInstance, entitiesInstances, rules, termination, spaceGrid);
        } catch (Exception e) {
            throw new SimulationEstablishmentException("An error occurred while trying to establish simulation");
        }
    }

    @Override
    public Map<String, EntityDefinition> createEntityDefinitionMap() {
        return new HashMap<>(this.worldDefinition.getEntities());
    }

    @Override
    public EnvironmentInstance establishEnvironment() {

        Map<String, PropertyInstance> environmentVariables =
                createPropertyInstances(
                        worldDefinition.getEnvironment().getEnvironmentProperties(),
                        DEFAULT_STARTING_TICK);

        return new EnvironmentInstanceImpl(environmentVariables);
    }

    @Override
    public Map<String, List<EntityInstance>> createAllEntitiesInstances() {

        Map<String , List<EntityInstance>> entitiesInstances = new HashMap<>();
        Map<String, EntityDefinition> entitiesDefinitions = this.worldDefinition.getEntities();
        entitiesDefinitions.forEach((entName, entDef) ->
                entitiesInstances.put(entName, createEntityInstances(entDef)));

        return entitiesInstances;
    }

    @Override
    public List<EntityInstance> createEntityInstances(EntityDefinition entityDefinition) {

        // Result :
        List<EntityInstance> primaryEntityInstances = new ArrayList<>();

        for (int index = 0; index < entityDefinition.getPopulation(); index++) {

            EntityInstance singlePrimaryInstance = createSingleEntityInstance(entityDefinition, index + 1, DEFAULT_STARTING_TICK);
            primaryEntityInstances.add(singlePrimaryInstance);
        }

        return primaryEntityInstances;
    }

    @Override
    public EntityInstance createSingleEntityInstance(EntityDefinition entityDefinition, int id, int tickNoForProperties) {

        EntityInstance singleInstance;

        Map<String, PropertyInstance> entityPropertyInstances =
                createPropertyInstances(entityDefinition.getProperties(), tickNoForProperties);

        singleInstance = new EntityInstanceImpl(entityDefinition.getName(), id, entityPropertyInstances);
        return singleInstance;
    }

    @Override
    public Map<String, PropertyInstance> createPropertyInstances(Map<String, AbstractPropertyDefinition> propertyDefinitions, int tickNo) {

        Map<String, PropertyInstance> propertyInstanceMap = new HashMap<>();

        for (Map.Entry<String, AbstractPropertyDefinition> entry :
                propertyDefinitions.entrySet()) {

            /// Note - we should validate key (bane) == value.getName(); in each propery Entry <K,V>

            String propName = entry.getKey();
            AbstractPropertyDefinition propDefinition = entry.getValue();

            PropertyInstance propertyInstance =
                    new PropertyInstanceImpl(propDefinition, propDefinition.generateValue(), tickNo);

            propertyInstanceMap.put(propName, propertyInstance);
        }

        return  propertyInstanceMap;
    }


    @Override
    public EstablishedEnvironmentInfoDto getEstablishedEnvironmentInfo() {

            Map<String, String> envInfo = new HashMap<>();

            Map<String, PropertyInstance> envPropInstances =
                    this.worldInstance.getEnvironmentInstance().getAllEnvironmentPropertiesInstances();

            for (Map.Entry<String, PropertyInstance> envPropInstance : envPropInstances.entrySet()) {
                envInfo.put(envPropInstance.getKey(),
                        PropertyType.STRING.convert(envPropInstance.getValue().getValue().toString()));
            }


            return new EstablishedEnvironmentInfoDto(envInfo);


    }

    @Override
    public WorldInstance getEstablishedWorldInstance() {
        return this.worldInstance;
    }

}
