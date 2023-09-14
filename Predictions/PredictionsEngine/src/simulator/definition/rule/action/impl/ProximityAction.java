package simulator.definition.rule.action.impl;

import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.runner.utils.exceptions.SimulatorRunnerException;
import structure.api.Coordinate;

import java.util.List;

public class ProximityAction extends AbstractAction {

    private String sourceEntityName;
    private String targetEntityName;
    private ArgumentExpression envDepth;
    List<AbstractAction> actionsUnderProximity;

    /**
     * Pay attention.
     * Decided to use AbstarctAction C'tor and set the primaryEntityName as the one should be the sourceEntity.
     */
    public ProximityAction(
            ActionType type, String sourceEntityName, String targetEntityName, ArgumentExpression envDepth, List<AbstractAction> actionsUnderProximity) {
        super(type, sourceEntityName);
        this.sourceEntityName = sourceEntityName;
        this.targetEntityName = targetEntityName;
        this.envDepth = envDepth;
        this.actionsUnderProximity = actionsUnderProximity;
    }

    @Override
    public void invoke(ExecutionContext executionContext) {

        // Get the coordinate of the srcEntity
        Coordinate srcEntityCoord = executionContext.getEntityInstanceByName(this.sourceEntityName).getCoordinate();

        // Get targetEntity instance in the envDepth - if there is!!!!
        // If so -> execute the Action...
        // Otherwise -> do nothing.

        throw new SimulatorRunnerException("Proximity action invoke not implemented");
    }
}
