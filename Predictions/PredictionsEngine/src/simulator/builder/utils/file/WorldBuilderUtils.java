package simulator.builder.utils.file;

import simulator.builder.utils.exception.WorldBuilderException;
import simulator.definition.rule.action.utils.enums.eConditionCompartorType;

public final class WorldBuilderUtils {

    public static eConditionCompartorType convertOperatorSignToComparatorType(String operatorSign) {
        if (operatorSign.equals("=")) {
            return eConditionCompartorType.EQAULITY;
        } else if (operatorSign.equals("!=")) {
            return eConditionCompartorType.INEQUALITY;
        } else if (operatorSign.equalsIgnoreCase("bt")) {
            return eConditionCompartorType.BIGGERTHAN;
        } else if (operatorSign.equalsIgnoreCase("lt")) {
            return eConditionCompartorType.LOWERTHAN;
        } else {
            throw new WorldBuilderException("Unknown condition comparator operator");
        }
    }





}
