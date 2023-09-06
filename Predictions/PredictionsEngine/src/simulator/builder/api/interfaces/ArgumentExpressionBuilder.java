package simulator.builder.api.interfaces;

import simulator.builder.utils.ArgExpressionContextDemands;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.method.*;
import simulator.definition.rule.action.expression.argumentExpression.impl.property.EntityPropertyArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.value.SimpleValueArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;

import java.util.Optional;

public interface ArgumentExpressionBuilder {
    ArgumentExpression buildExpression(String rawExpression, ArgExpressionContextDemands expressionContextDemands);
    AbstractMethodArgumentExpression buildMethodArgumentExpression(ExpressionMethodType expMethodType, String rawExpression, ArgExpressionContextDemands expressionContextDemands);
    EntityPropertyArgumentExpression buildPropertyNameArgumentExpression(String entityPropertyName, ArgExpressionContextDemands expressionContextDemands);
    SimpleValueArgumentExpression buildValueArgumentExpression(String rawExpression, ArgExpressionContextDemands expressionContextDemands);
    String extractParenthesesString(String rawExpression);
    Optional<ExpressionMethodType> tryGetMethodType(String rawExpression, ArgExpressionContextDemands expressionContextDemands);
    Optional<String> tryGetEntityPropertyName(String rawExpression, ArgExpressionContextDemands expressionContextDemands);
    EnvironmentMethodArgumentExpression buildEnvironmentMethodExpression(String rawEnvPropString, ArgExpressionContextDemands expressionContextDemands);
    RandomMethodArgumentExpression buildRandomMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands);
    EvaluateMethodArgumentExpression buildEvaluateMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands);
    TicksMethodArgumentExpression buildTicksMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands);
    PercentMethodArgumentExpression buildPercentMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands);

}
