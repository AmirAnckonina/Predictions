package simulator.builder.impl.baseImpl;

import simulator.builder.api.interfaces.ArgumentExpressionBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.definition.rule.action.argumentExpression.utils.enums.eExpressionMethod;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.argumentExpression.api.interfaces.ArgumentExpression;
import simulator.definition.rule.action.argumentExpression.impl.EnvironmentMethodArgumentExpressionImpl;
import simulator.definition.rule.action.argumentExpression.impl.PropertyArgumentExpressionImpl;
import simulator.definition.rule.action.argumentExpression.impl.RandomMethodArgumentExpressionImpl;
import simulator.definition.rule.action.argumentExpression.impl.ValueArgumentExpressionImpl;
import java.util.Random;

public class BaseArgumentExpressionBuilder extends AbstractComponentBuilder implements ArgumentExpressionBuilder {

    public BaseArgumentExpressionBuilder(WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
    }

    @Override
    public ArgumentExpression buildExpression(String rawExpression, ePropertyType type) {
        ArgumentExpression argumentExpression = null;
        if (isIdentifiesEnvironmentMethod(rawExpression)){
            String method = getEnvironmentMethodName(rawExpression);
            switch (eExpressionMethod.valueOf(method.toUpperCase())){
                case ENVIRONMENT:
                    if(contextValidator.isEnvironmentProperty(getEnvironmentDataMemberName(rawExpression)) &&
                            (this.contextValidator
                                    .validateEnvironemntPropertyTypeAsExpected(
                                            getEnvironmentDataMemberName(rawExpression) ,type) ) ) {

                        argumentExpression = new EnvironmentMethodArgumentExpressionImpl(
                                eExpressionMethod.ENVIRONMENT,
                                getEnvironmentDataMemberName(rawExpression));
                        break;
                    }
                    else {
                        throw new WorldBuilderException("Cannot build Environemnt method Expression");
                    }

                case RANDOM:
                    argumentExpression = new RandomMethodArgumentExpressionImpl(eExpressionMethod.RANDOM, (new Random()).nextInt());
                    break;
            }

        } else if (contextValidator.isEnvironmentProperty(rawExpression)) {
            
            if (this.contextValidator.validateEnvironemntPropertyTypeAsExpected(
                    rawExpression, type)) {
                argumentExpression = new PropertyArgumentExpressionImpl(rawExpression);
            }
        } else {
            switch (type) {
                case BOOLEAN:
                    Boolean booleanValue = Boolean.parseBoolean(rawExpression);
                    argumentExpression = new ValueArgumentExpressionImpl(booleanValue);
                    break;
                case STRING:
                    String stringValue = rawExpression;
                    argumentExpression = new ValueArgumentExpressionImpl(stringValue);

                    break;
                case FLOAT:
                    Float floatValue = Float.parseFloat(rawExpression);
                    argumentExpression = new ValueArgumentExpressionImpl(floatValue);

                    break;
                case DECIMAL:
                    Integer intValue = Integer.parseInt(rawExpression);
                    argumentExpression = new ValueArgumentExpressionImpl(intValue);
                    break;
                default:
                    throw new WorldBuilderException("The raw expression" + rawExpression + "can't be recognized");
            }
        }

        return argumentExpression;
    }

    private String getEnvironmentDataMemberName(String rawExpression) {
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