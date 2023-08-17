package simulator.builder.world.impl;

import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ExpressionBuilder;
import simulator.builder.world.utils.enums.eExpressionExpectedValueType;
import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.expression.api.AbstractMethodExpression;
import simulator.definition.rule.action.expression.impl.EnvironmentMethodExpressionImpl;
import simulator.definition.rule.action.expression.impl.PropertyExpressionImpl;
import simulator.definition.rule.action.expression.impl.RandomMethodExpressionImpl;
import simulator.definition.rule.action.expression.impl.ValueExpressionImpl;
import java.util.Random;

public class BaseExpressionBuilder extends AbstractComponentBuilder implements ExpressionBuilder {

    public BaseExpressionBuilder(WorldBuilderContextValidator contextValidator, ExpressionBuilder) {
        super(contextValidator);
    }

    @Override
    public Expression buildExpression(String rawExpression, ePropertyType type) {
        Expression expression = null;
        if(isIdentifiesEnvironmentMethod(rawExpression)){
            String method = getEnvironmentMethodName(rawExpression);
            switch (eExpressionMethod.valueOf(method.toUpperCase())){
                case ENVIRONMENT:
                    if(contextValidator.isEnvironmentProperty(rawExpression) &&
                            (this.contextValidator.isAppropriateType(rawExpression, ePropertyType.valueOf(type.toString())))){
                        expression = new EnvironmentMethodExpressionImpl(eExpressionMethod.ENVIRONMENT, getEnvironmentDataMemberName(rawExpression));
                    }
                    else throw new RuntimeException();
                    break;

                case RANDOM:
                    if(type != ePropertyType.STRING){
                        throw new RuntimeException();
                    }
                    expression = new RandomMethodExpressionImpl(eExpressionMethod.RANDOM, (new Random()).nextInt());
                    break;
            }

        } else if (contextValidator.isEnvironmentProperty(rawExpression)) {
            if(this.contextValidator.isAppropriateType(rawExpression, ePropertyType.valueOf(type.toString()))){
                expression = new PropertyExpressionImpl(rawExpression);
            }
        }else {
            switch (type) {

                case DECIMAL:
                    Double doubleValue = Double.parseDouble(rawExpression);
                    expression = new ValueExpressionImpl(doubleValue);
                    break;
                case BOOLEAN:
                    Boolean booleanValue = Boolean.parseBoolean(rawExpression);
                    expression = new ValueExpressionImpl(booleanValue);
                    break;
                case STRING:
                    String stringValue = rawExpression;
                    expression = new ValueExpressionImpl(stringValue);

                    break;
                case FLOAT:
                    Float floatValue = Float.parseFloat(rawExpression);
                    expression = new ValueExpressionImpl(floatValue);

                    break;
                case INTEGER:
                    Integer intValue = Integer.parseInt(rawExpression);
                    expression = new ValueExpressionImpl(intValue);
                    break;
            }
        }

        return expression;
    }

    private String getEnvironmentDataMemberName(String rawExpression){
        return rawExpression.substring(eExpressionMethod.ENVIRONMENT.toString().length() + 1, rawExpression.length() - 1);
    }

    private boolean isEnvironmentProperty(String rawExpression) {
        return this.contextValidator.isEnvironmentProperty(rawExpression);
    }

    private boolean isIdentifiesEnvironmentMethod(String rawExpression) {
        return rawExpression.startsWith("environment") ||
                rawExpression.startsWith("random");
    }
    
    private String getEnvironmentMethodName(String rawExpression){
        String res = null;
        
        if(rawExpression.startsWith("environment")){
            res = "environment";
        } else if (rawExpression.startsWith("random")) {
            res = "random";
        } else if (rawExpression.startsWith("evaluate")) {
            res = "evaluate";
        } else if (rawExpression.startsWith("percent")) {
            res = "percent";
        } else if (rawExpression.startsWith("ticks")) {
            res = "ticks";
        }

        return res;
    }

}
