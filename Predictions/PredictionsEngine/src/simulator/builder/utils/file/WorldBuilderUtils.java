package simulator.builder.utils.file;

import simulator.builder.utils.exception.WorldBuilderManagerException;
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
            throw new WorldBuilderManagerException("Unknown condition comparator operator");
        }
    }





}
