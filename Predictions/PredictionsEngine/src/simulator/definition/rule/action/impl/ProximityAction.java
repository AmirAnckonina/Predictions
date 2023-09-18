package simulator.definition.rule.action.impl;

import enums.PropertyType;
import simulator.definition.rule.action.api.abstracts.AbstractAction;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import enums.ActionType;
import simulator.execution.context.api.ExecutionContext;
import simulator.execution.instance.entity.api.EntityInstance;
import simulator.runner.utils.exceptions.SimulatorRunnerException;
import structure.coordinate.api.Coordinate;

import java.util.List;
import java.util.Optional;

public class ProximityAction extends AbstractAction {

    private String sourceEntityName;
    private String targetEntityName;
    private ArgumentExpression envDepth;
    List<AbstractAction> actionsUnderProximity;

    @Override
    public String toString() {
        String actionsSize = (actionsUnderProximity == null)?"0":Integer.toString(actionsUnderProximity.size());
        return "Proximity{" +
                "sourceEntityName='" + sourceEntityName + '\'' +
                ", targetEntityName='" + targetEntityName + '\'' +
                ", envDepth=" + envDepth +
                ", actionsUnderProximity=" + actionsSize +
                ", type=" + type +
                ", primaryEntityName='" + primaryEntityName + '\'' +
                ", actionSecondaryEntityDefinition=" + actionSecondaryEntityDefinition +
                '}' + System.lineSeparator();
    }

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
        Coordinate srcEntityCoordinate = executionContext.getEntityInstanceByName(this.sourceEntityName).getCoordinate();
        Integer envCircleDepthValue = Math.round(PropertyType.FLOAT.convert(this.envDepth.getValue(executionContext)));

        Optional<EntityInstance> maybeTargetEntityInstance =
                executionContext.searchEntityInstance(srcEntityCoordinate, this.targetEntityName, envCircleDepthValue);

        maybeTargetEntityInstance
                .ifPresent(targetInstance -> {
                    executionContext.setSecondaryEntityInstance(targetInstance);
                    actionsUnderProximity.forEach((action) -> action.invoke(executionContext));
                });
    }
}
