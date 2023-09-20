package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import enums.ActionType;
import simulator.definition.rule.action.utils.enums.ReplaceActionCreationMode;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;
import structure.coordinate.api.Coordinate;

public class ReplaceAction extends AbstractAction {
    private String entityNameToKill;
    private String entityNameToCreate;
    private ReplaceActionCreationMode replaceActionCreationMode;

    @Override
    public String toString() {
        return "Replace{" +
                "entityNameToKill='" + entityNameToKill + '\'' +
                ", entityNameToCreate='" + entityNameToCreate + '\'' +
                ", replaceActionCreationMode=" + replaceActionCreationMode +
                ", type=" + type +
                ", primaryEntityName='" + primaryEntityName + '\'' +
                ", actionSecondaryEntityDefinition=" + actionSecondaryEntityDefinition +
                '}' + System.lineSeparator();
    }

    public ReplaceAction(
            ActionType type,
            String entityNameToKill,
            String entityNameToCreate,
            ReplaceActionCreationMode replaceActionCreationMode) {
        /**
         * Pay attention.
         * Decided to use AbstarctAction C'tor and set the primaryEntityName as the one should be killed.
         */
        super(type, entityNameToKill);
        this.entityNameToKill = entityNameToKill;
        this.entityNameToCreate = entityNameToCreate;
        this.replaceActionCreationMode = replaceActionCreationMode;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {

        EntityInstance instanceToKill = executionContext.getEntityInstanceByName(this.entityNameToKill);
        Coordinate replaceCoordinate = executionContext.getEntityInstanceByName(this.entityNameToKill).getCoordinate();
        EntityInstance createdEntityInstance = executionContext.createEntityInstanceProcedure(this.entityNameToCreate);
        createdEntityInstance.setCoordinate(replaceCoordinate);

        executionContext
                .getCrossedExecutionContext()
                .getSpaceGridInstanceWrapper()
                .setCellAsReserved(replaceCoordinate);

        if (replaceActionCreationMode == ReplaceActionCreationMode.DERIVED) {
            executionContext.derivePropertiesBetweenInstancesProcedure(createdEntityInstance, instanceToKill);
        }

        executionContext.killEntityInstanceProcedure(instanceToKill);
    }
}
