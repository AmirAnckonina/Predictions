package simulator.builder.api.interfaces;

import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.method.*;
import simulator.definition.rule.action.expression.argumentExpression.impl.property.PropertyNameArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.value.SimpleValueArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;

import java.util.Optional;

public interface ArgumentExpressionBuilder {
    ArgumentExpression buildExpression(String rawExpression, ArgExpressionTypeDemands typeDemands);

    AbstractMethodArgumentExpression buildMethodArgumentExpression(
            eExpressionMethod expMethodType, String rawExpression, ArgExpressionTypeDemands typeDemands);

    PropertyNameArgumentExpression buildPropertyNameArgumentExpression(
            String entityPropertyName, ArgExpressionTypeDemands typeDemands);

    SimpleValueArgumentExpression buildValueArgumentExpression(
            String rawExpression, ArgExpressionTypeDemands typeDemands);

    String extractParenthesesString(String rawExpression);

    Optional<eExpressionMethod> tryGetMethodType(
            String rawExpression, ArgExpressionTypeDemands typeDemands);

    Optional<String> tryGetEntityPropertyName(
            String rawExpression, ArgExpressionTypeDemands typeDemands);

    EnvironmentMethodArgumentExpression buildEnvironmentMethodExpression(String rawEnvPropString, ArgExpressionTypeDemands typeDemands);
    RandomMethodArgumentExpression buildRandomMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands);
    EvaluateMethodArgumentExpression buildEvaluateMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands);
    TicksMethodArgumentExpression buildTicksMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands);
    PercentMethodArgumentExpression buildPercentMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands);

}
