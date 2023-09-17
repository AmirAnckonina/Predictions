package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import enums.ActionType;
import simulator.definition.rule.action.utils.enums.ReplaceActionCreationMode;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

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
        instanceToKill.killMyself();

        // Impl creation of new Entity according to the mode

        throw new SimulatorRunnerException("Not impl replace action");

    }
}
