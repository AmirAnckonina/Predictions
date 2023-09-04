package simulator.definition.rule.action.secondaryEntity.impl;

import simulator.definition.rule.action.expression.conditionExpression.api.interfaces.ConditionExpression;
import simulator.definition.rule.action.impl.ConditionAction;
import simulator.definition.rule.action.secondaryEntity.api.ActionSecondaryEntityDefinition;
import simulator.definition.rule.action.utils.enums.eSecondaryEntitySelectionType;

import java.util.Optional;

public class ActionSecondaryEntityDefinitionImpl implements ActionSecondaryEntityDefinition {

    private String secondaryEntityName;
    private ConditionExpression conditionExpression;
    private eSecondaryEntitySelectionType secondaryEntitySelectionType;
    private Integer selectionCount;

    public ActionSecondaryEntityDefinitionImpl(
            String secondaryEntityName,
            ConditionExpression conditionExpression,
            eSecondaryEntitySelectionType secondaryEntitySelectionType,
            Integer selectionCount) {

        this.secondaryEntityName = secondaryEntityName;
        this.conditionExpression = conditionExpression;
        this.secondaryEntitySelectionType = secondaryEntitySelectionType;
        this.selectionCount = selectionCount;
    }

    public ActionSecondaryEntityDefinitionImpl(
            String secondaryEntityName,
            ConditionExpression conditionExpression,
            eSecondaryEntitySelectionType secondaryEntitySelectionType) {

        this(secondaryEntityName, conditionExpression, secondaryEntitySelectionType, null);
    }

    @Override
    public String getSecondaryEntityName() {
        return this.secondaryEntityName;
    }

    @Override
    public Optional<Integer> getSelectionCount() {
        return Optional.ofNullable(this.selectionCount);
    }

    @Override
    public eSecondaryEntitySelectionType getSecondaryEntitySelectionType() {
        return this.secondaryEntitySelectionType;
    }

    @Override
    public ConditionExpression getConditionExpression() {
        return this.conditionExpression;
    }
}