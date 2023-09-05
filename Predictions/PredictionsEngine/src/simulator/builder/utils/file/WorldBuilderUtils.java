package simulator.builder.utils.file;

import simulator.builder.utils.exception.WorldBuilderException;
import simulator.definition.rule.action.utils.enums.ConditionCompartorType;

public final class WorldBuilderUtils {

    public static ConditionCompartorType convertOperatorSignToComparatorType(String operatorSign) {
        if (operatorSign.equals("=")) {
            return ConditionCompartorType.EQAULITY;
        } else if (operatorSign.equals("!=")) {
            return ConditionCompartorType.INEQUALITY;
        } else if (operatorSign.equalsIgnoreCase("bt")) {
            return ConditionCompartorType.BIGGERTHAN;
        } else if (operatorSign.equalsIgnoreCase("lt")) {
            return ConditionCompartorType.LOWERTHAN;
        } else {
            throw new WorldBuilderException("Unknown condition comparator operator");
        }
    }





}
