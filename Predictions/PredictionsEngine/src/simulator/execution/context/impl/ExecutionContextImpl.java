package simulator.execution.context.impl;

import simulator.definition.entity.EntityDefinition;
import simulator.execution.context.api.CrossedExecutionContext;
import simulator.execution.instance.spaceGrid.api.SpaceGridInstanceWrapper;
import simulator.execution.context.api.ExecutionContext;

import simulator.execution.instance.entity.api.EntityInstance;
import simulator.execution.instance.property.api.PropertyInstance;
import simulator.information.tickDocument.api.TickDocument;
import structure.coordinate.api.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExecutionContextImpl implements ExecutionContext {
    private CrossedExecutionContext crossedExecutionContext;
    private Map<String, EntityInstance> entityInstanceMap;
    private TickDocument currTickDocument;

    public ExecutionContextImpl(CrossedExecutionContext crossedExecutionContext, EntityInstance primaryEntityInstance, TickDocument currTickDocument) {
        this.crossedExecutionContext = crossedExecutionContext;
        this.entityInstanceMap = new HashMap<>();
        this.entityInstanceMap.put(primaryEntityInstance.getEntityNameFamily(), primaryEntityInstance);
        this.currTickDocument = currTickDocument;
    }

    @Override
    public EntityInstance getEntityInstanceByName(String entityName) {
        return this.entityInstanceMap.get(entityName);
    }

    @Override
    public PropertyInstance getEnvironmentVariable(String name) {
        return this.crossedExecutionContext.getEnvironmentInstance().getPropertyByName(name);
    }

    @Override
    public void setSecondaryEntityInstance(EntityInstance secondaryEntityInstance) {
        this.entityInstanceMap.put(secondaryEntityInstance.getEntityNameFamily(), secondaryEntityInstance);
    }

    @Override
    public TickDocument getTickDocument() { return this.currTickDocument; }

    @Override
    public SpaceGridInstanceWrapper getSpaceGridInstanceWrapper() {
        return this.crossedExecutionContext.getSpaceGridInstanceWrapper();
    }

    @Override
    public void killEntityInstanceProcedure(EntityInstance entityInstanceToKill) {
        entityInstanceToKill.killMyself();
        //this.crossedExecutionContext.getSpaceGridInstanceWrapper().clearCellByCoordinate(entityInstanceToKill.getCoordinate());
        this.crossedExecutionContext.getSpaceGridInstanceWrapper().setCellAsReserved(entityInstanceToKill.getCoordinate());
        this.crossedExecutionContext.getEntitiesInstancesManager().addInstanceToKillWaitingList(entityInstanceToKill);
    }

    @Override
    public EntityInstance createEntityInstanceProcedure(String entityFamilyName) {

        EntityInstance createdEntityInstance;
        EntityDefinition entityDefinition = this.crossedExecutionContext.getEntityDefinitionByName(entityFamilyName);
        createdEntityInstance =
                this.crossedExecutionContext
                        .getEntitiesInstancesManager()
                        .createEntityInstanceByDefinition(entityDefinition, this.currTickDocument.getTickNumber());

        return createdEntityInstance;
    }

    @Override
    public CrossedExecutionContext getCrossedExecutionContext() {
        return this.crossedExecutionContext;
    }

    @Override
    public void derivePropertiesBetweenInstancesProcedure(EntityInstance targetEntityInstance, EntityInstance sourceEntityInstance) {
        this.crossedExecutionContext
                .getEntitiesInstancesManager()
                .derivePropertiesBetweenInstances(targetEntityInstance, sourceEntityInstance, this.currTickDocument);
    }

    @Override
    public Optional<EntityInstance> searchEntityInstance(Coordinate srcEntityCoordinate, String targetEntityName, Integer envCircleDepthValue) {
        return this.crossedExecutionContext
                .getSpaceGridInstanceWrapper()
                .searchEntityInstance(srcEntityCoordinate, targetEntityName, envCircleDepthValue);
    }
}
