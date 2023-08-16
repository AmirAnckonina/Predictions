package simulator.builder.world.api;

import simulator.definition.rule.action.api.AbstractAction;
import simulator.definition.rule.action.api.AbstractCalculationAction;
import simulator.definition.rule.action.impl.*;
import simulator.definition.rule.action.utils.eActionType;


public interface ActionBuilder {
    /*1	Increase	1
2	Decrease	1
3	Calculation	1
4	Condition	1
5	Set	1
6	Kill	1
7	Replace	2
8	Proximity	2
*/
    AbstractAction BuildAction();
    IncreaseAction buildIncreaseAction();
    DecreaseAction buildDecreaseAction();
    AbstractCalculationAction buildCalculationAction();
    MultiplyAction buildMultiplyAction();
    DivideAction buildDivideAction();
    ConditionAction buildConditionAction();
    SetAction buildSetAction();
    KillAction buildKillAction();
}
