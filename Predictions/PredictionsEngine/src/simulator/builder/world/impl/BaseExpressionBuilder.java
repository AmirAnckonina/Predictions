package simulator.builder.world.impl;

import simulator.builder.world.api.AbstractComponentBuilder;
import simulator.builder.world.api.ExpressionBuilder;
import simulator.builder.world.utils.enums.eExpressionExpectedValueType;
import simulator.builder.world.utils.enums.eExpressionMethod;
import simulator.builder.world.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.enums.ePropertyType;
import simulator.definition.rule.action.expression.api.Expression;
import simulator.definition.rule.action.expression.impl.MethodExpressionImpl;
import simulator.definition.rule.action.expression.impl.ValueExpressionImpl;
import java.util.Random;

public class BaseExpressionBuilder extends AbstractComponentBuilder implements ExpressionBuilder {

    public BaseExpressionBuilder(WorldBuilderContextValidator contextValidator, ExpressionBuilder) {
        super(contextValidator);
    }

    @Override
    public Expression buildExpression(String rawExpression, eExpressionExpectedValueType type) {
        Expression expression = null;
        if(rawExpression.toUpperCase().equals(eExpressionMethod.RANDOM.toString()) ||
            rawExpression.toUpperCase().equals(eExpressionMethod.ENVIRONMENT.name())){
            switch (eExpressionMethod.valueOf(rawExpression.toUpperCase())){
                case ENVIRONMENT:
                    if(contextValidator.validateEnvironmentPropertyExist(rawExpression) &&
                            (this.contextValidator.validateEnvironemntPropertyTypeAsExpected(rawExpression, type))){
                        expression = new MethodExpressionImpl(eExpressionMethod.ENVIRONMENT, getByValue(rawExpression));
                    }
                    else throw new RuntimeException();
                    break;
                case RANDOM:
                    expression = new MethodExpressionImpl(eExpressionMethod.ENVIRONMENT, (new Random()).nextInt());
                    break;
            }

        } else if (contextValidator.validateEnvironmentPropertyExist(rawExpression)) {
            if(this.contextValidator.validateEnvironemntPropertyTypeAsExpected(rawExpression, ePropertyType.valueOf(type.toString()))){
                //implement expression
            }
        }else {
            switch (type) {

                case DECIMAL:
                    Double doubleValue = Double.parseDouble(rawExpression);
                    expression = new ValueExpressionImpl(doubleValue);
                    break;
                case BOOLEAN:
                    Boolean booleanValue = Boolean.parseBoolean(rawExpression);
                    expression = new ValueExpressionImpl<Boolean>(booleanValue);
                    break;
                case STRING:
                    String stringValue = rawExpression;
                    expression = new ValueExpressionImpl<String>(stringValue);

                    break;
                case FLOAT:
                    Float floatValue = Float.parseFloat(rawExpression);
                    expression = new ValueExpressionImpl<Float>(floatValue);

                    break;
                case INTEGER:
                    Integer intValue = Integer.parseInt(rawExpression);
                    expression = new ValueExpressionImpl<Integer>(intValue);
                    break;
            }
        }

        return expression;
    }

    private String getByValue(String rawExpression) {
        String cleanValue = getCleanRowExpression(rawExpression);
        if(cleanValue.startsWith(eExpressionMethod.ENVIRONMENT.toString())){
            return
        }
    }

    private String getCleanRowExpression(String exppresion){
        return exppresion.substring(4, exppresion.length() - 1);
    }

    private String getEnvironmentDataMemberName(String cleanValue){
        return cleanValue.substring(eExpressionMethod.ENVIRONMENT.toString().length() + 1, cleanValue.length() - 1);

    }


    private boolean isEnvironmentProperty(WorldBuilderContextValidator contextValidator) {
    }

    private boolean isIdentifiesEnvironmentMethod(String rawExpression) {
    }

}
