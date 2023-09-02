package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.eActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;

public class ProximityAction extends AbstractAction {

    private String sourceEntityName;
    private String targetEntityName;
    private ArgumentExpression envDepth;

    public ProximityAction(
            eActionType type,
            String sourceEntityName,
            String targetEntityName,
            ArgumentExpression envDepth
            ) {
        /**
         * Pay attention.
         * Decided to use AbstarctAction C'tor and set the primaryEntityName as the one should be the sourceEntity.
         */
        super(type, sourceEntityName);
        this.sourceEntityName = sourceEntityName;
        this.targetEntityName = targetEntityName;
        this.envDepth = envDepth;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {
        throw new SimulatorRunnerException("Proximity action invoke not implemented");
    }
}
