package simulator.builder.impl.baseImpl;

import simulator.builder.api.interfaces.ArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.builder.utils.MandatoryTypeDemanding;
import simulator.builder.utils.ExtractedParenthesesPart;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.definition.property.utils.enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.method.*;
import simulator.definition.rule.action.expression.argumentExpression.impl.property.PropertyNameArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.value.SimpleValueArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.ExpressionMethodType;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.rule.action.expression.argumentExpression.api.interfaces.ArgumentExpression;

import java.util.Optional;
import java.util.Random;

public class BaseArgumentExpressionBuilder extends AbstractComponentBuilder implements ArgumentExpressionBuilder {

    private static final char PERIOD = '.';
    private static final char COMMA = ',';


    public BaseArgumentExpressionBuilder(WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
    }

    @Override
    public ArgumentExpression buildExpression(String rawExpression, ArgExpressionTypeDemands typeDemands) {

        ArgumentExpression argExpression;

        Optional<ExpressionMethodType> maybeExpressionMethodType = tryGetMethodType(rawExpression, typeDemands);
        if (maybeExpressionMethodType.isPresent()) {

            argExpression = buildMethodArgumentExpression(
                    maybeExpressionMethodType.get(), rawExpression, typeDemands);

        } else {

            Optional<String> maybeEntityPropertyName = tryGetEntityPropertyName(rawExpression, typeDemands);
            if (maybeEntityPropertyName.isPresent()) {
                argExpression = buildPropertyNameArgumentExpression(maybeEntityPropertyName.get(), typeDemands);
            }
            else {
                // Here we're trying to build ValueArgExpression
                argExpression = buildValueArgumentExpression(rawExpression, typeDemands);
            }
        }

        return argExpression;
    }

    @Override
    public AbstractMethodArgumentExpression buildMethodArgumentExpression(
            ExpressionMethodType expMethodType, String rawExpression, ArgExpressionTypeDemands typeDemands) {

        AbstractMethodArgumentExpression methodArgExpression;
        String parenthesesStringValue = extractParenthesesString(rawExpression);

        switch (expMethodType) {
            case ENVIRONMENT: //environment(e1)
                methodArgExpression = buildEnvironmentMethodExpression(parenthesesStringValue , typeDemands);
                break;

            case RANDOM: // random(5)
                methodArgExpression = buildRandomMethodArgumentExpression(parenthesesStringValue, typeDemands);
                break;

            case EVALUATE: //evaluate(ent-1.p1)
                methodArgExpression = buildEvaluateMethodArgumentExpression(parenthesesStringValue, typeDemands);
                break;

             case TICKS: // ticks(Sick.vacinated)
                methodArgExpression = buildTicksMethodArgumentExpression(parenthesesStringValue, typeDemands);
                break;

            case PERCENT: // percent(evalutate(ent-2.p1),environment(e1))
                methodArgExpression = buildPercentMethodArgumentExpression(parenthesesStringValue, typeDemands);
                break;

            default:
                throw new WorldBuilderException("Unrecognized method expression, can't build");
        }

        return methodArgExpression;
    }


    @Override
    public EnvironmentMethodArgumentExpression buildEnvironmentMethodExpression(String rawEnvPropString, ArgExpressionTypeDemands typeDemands) {

        EnvironmentMethodArgumentExpression environmentMethodArgumentExpression;

        boolean typeValid =
                contextValidator.validateEnvironemntPropertyTypeAsExpected(
                        rawEnvPropString, typeDemands.getPropertyType());

        boolean envPropertyValid = contextValidator.isEnvironmentProperty(rawEnvPropString);

        if (!envPropertyValid || !typeValid) {
            throw new WorldBuilderException("Cannot build Environemnt method Expression");
        }

       environmentMethodArgumentExpression = new EnvironmentMethodArgumentExpression(
                ExpressionMethodType.ENVIRONMENT,
                contextValidator.getEnvironmentPropertyType(rawEnvPropString), //Should return as String
                rawEnvPropString

       );


        return environmentMethodArgumentExpression;
    }

    @Override
    public RandomMethodArgumentExpression buildRandomMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands) {
        
        Integer maxRandVal = Integer.parseInt(parenthesesStringValue);
        return new RandomMethodArgumentExpression(
                ExpressionMethodType.RANDOM,
                PropertyType.DECIMAL, // Maybe as a float?
                (new Random()).nextInt(maxRandVal)
        );
    }

    @Override
    public EvaluateMethodArgumentExpression buildEvaluateMethodArgumentExpression(
            String parenthesesStringValue, ArgExpressionTypeDemands typeDemands) {

        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.RIGHT);

        boolean entityAndPropertyContextValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propTypeAsExpected;
        if (typeDemands.getMandatoryDemand() == MandatoryTypeDemanding.Mentioned) {
                propTypeAsExpected = contextValidator.validateEntityPropertyAsExpected(
                        entityName, propertyName, typeDemands.getPropertyType()
                );
        } else { propTypeAsExpected = true; }


        if (!entityAndPropertyContextValid || !propTypeAsExpected) {
            throw new WorldBuilderException(
                    "evaluate Method couldn't be build because entity or property context is incorrect.");
        }


        return new EvaluateMethodArgumentExpression(
                ExpressionMethodType.EVALUATE,
                contextValidator.getEntityPropertyType(entityName, propertyName),
                entityName,
                propertyName
        );
    }

    @Override
    public TicksMethodArgumentExpression buildTicksMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands) {
        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.RIGHT);

        boolean entityAndPropertyValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propTypeAsExpected;
        if (typeDemands.getMandatoryDemand() != MandatoryTypeDemanding.NotMentioned) {
            propTypeAsExpected = contextValidator.validateEntityPropertyAsExpected(
                    entityName, propertyName, typeDemands.getPropertyType()
            );
        } else { propTypeAsExpected = true; }

        if (!entityAndPropertyValid || !propTypeAsExpected) {
            throw new WorldBuilderException(
                    "ticks Method couldn't be build because entity or property context is incorrect.");
        }

        return new TicksMethodArgumentExpression(
                ExpressionMethodType.TICKS,
                contextValidator.getEntityPropertyType(entityName, propertyName),
                entityName,
                propertyName);
    }

    @Override
    public PercentMethodArgumentExpression buildPercentMethodArgumentExpression(String parenthesesStringValue, ArgExpressionTypeDemands typeDemands) {

        String rawOriginalValueString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, ExtractedParenthesesPart.LEFT);
        String rawPercentageString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, ExtractedParenthesesPart.RIGHT);


        ArgumentExpression originalValueExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawOriginalValueString,
                                new ArgExpressionTypeDemands(
                                        PropertyType.FLOAT,
                                        MandatoryTypeDemanding.Mentioned
                                )
                        );
        ArgumentExpression percentageExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawPercentageString,
                                new ArgExpressionTypeDemands(
                                        PropertyType.FLOAT,
                                        MandatoryTypeDemanding.Mentioned
                                )
                        );

        return new PercentMethodArgumentExpression(
                ExpressionMethodType.PERCENT,
                PropertyType.FLOAT,
                originalValueExpression,
                percentageExpression);
    }

    private String extractSubStringInParenthesesString(
            String parenthesesStringValue, char delimiter, ExtractedParenthesesPart parenthesesPart) {

        String extractedPart;

        if(parenthesesPart == ExtractedParenthesesPart.LEFT) {

            extractedPart = parenthesesStringValue.substring(
                    0, parenthesesStringValue.lastIndexOf(delimiter));

        } else if (parenthesesPart == ExtractedParenthesesPart.RIGHT) {

            extractedPart = parenthesesStringValue.substring(
                    parenthesesStringValue.indexOf(delimiter) + 1 ,
                    parenthesesStringValue.length());

        } else {
            throw new WorldBuilderException(
                    "failed to extract parenthesesPart under buildArgExpression");
        }

        return extractedPart.trim();
    }


    @Override
    public PropertyNameArgumentExpression buildPropertyNameArgumentExpression(
            String entityPropertyName, ArgExpressionTypeDemands typeDemands) {
        // Why Why Why ????????
        // Is that because we need to build EntityPropertyExpression in case of
        // ticks(ent1.p1) ? so ent1.p1 is an expression?
        // Or maybe for the property under condition? so when we build argExpression we use this case

        //boolean
        return new PropertyNameArgumentExpression(
                entityPropertyName,
                contextValidator.getEntityPropertyTypeWithoutEntityNameMentioned(entityPropertyName)
        );
    }

    @Override
    public SimpleValueArgumentExpression buildValueArgumentExpression(
            String rawExpression, ArgExpressionTypeDemands typeDemands) {

        SimpleValueArgumentExpression simpleValueExpression;
        PropertyType propType = typeDemands.getPropertyType();

        switch (propType) {
            case BOOLEAN:
                Boolean booleanValue = Boolean.parseBoolean(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(booleanValue , PropertyType.BOOLEAN);
                break;
            case STRING:
                String stringValue = rawExpression;
                simpleValueExpression = new SimpleValueArgumentExpression(stringValue, PropertyType.STRING);

                break;
            case FLOAT:
                Float floatValue = Float.parseFloat(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(floatValue, PropertyType.FLOAT);

                break;
            case DECIMAL:
                Integer intValue = Integer.parseInt(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(intValue, PropertyType.DECIMAL);
                break;
            default:
                throw new WorldBuilderException("The raw expression" + rawExpression + "can't be recognized");
        }

        return simpleValueExpression;
    }

    @Override
    public String extractParenthesesString(String rawExpression) {
        return rawExpression.substring(rawExpression.indexOf('(') + 1, rawExpression.lastIndexOf(')'));
    }



    @Override
    public Optional<ExpressionMethodType> tryGetMethodType(
            String rawExpression, ArgExpressionTypeDemands typeDemands) {

        ExpressionMethodType method = null;

        if (rawExpression.startsWith("environment")){
            method = ExpressionMethodType.ENVIRONMENT;
        } else if (rawExpression.startsWith("random")) {
            method = ExpressionMethodType.RANDOM;
        } else if (rawExpression.startsWith("evaluate")) {
            method = ExpressionMethodType.EVALUATE;
        } else if (rawExpression.startsWith("percent")) {
            method = ExpressionMethodType.PERCENT;
        } else if (rawExpression.startsWith("ticks")) {
            method = ExpressionMethodType.TICKS;
        }

        return Optional.ofNullable(method);
    }

    @Override
    public Optional<String> tryGetEntityPropertyName(
            String rawExpression, ArgExpressionTypeDemands typeDemands) {

        String entityPropertyName = null;
        boolean entityPropertyNameExist = contextValidator.isEntityPropertyNameExists(rawExpression);

        if (entityPropertyNameExist) {
            entityPropertyName = rawExpression;
        }

        return Optional.ofNullable(entityPropertyName);
    }

}
