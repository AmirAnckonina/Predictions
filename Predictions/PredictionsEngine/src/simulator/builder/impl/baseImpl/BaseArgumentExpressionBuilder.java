package simulator.builder.impl.baseImpl;

import simulator.builder.api.interfaces.ArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionTypeDemands;
import simulator.builder.utils.eMandatoryTypeDemanding;
import simulator.builder.utils.eParenthesesPart;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.definition.property.utils.enums.ePropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.method.*;
import simulator.definition.rule.action.expression.argumentExpression.impl.property.PropertyNameArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.value.SimpleValueArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.utils.enums.eExpressionMethod;
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

        Optional<eExpressionMethod> maybeExpressionMethodType = tryGetMethodType(rawExpression, typeDemands);
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
            eExpressionMethod expMethodType, String rawExpression, ArgExpressionTypeDemands typeDemands) {

        AbstractMethodArgumentExpression methodArgExpression;
        String parenthesesStringValue = extractParenthesesString(rawExpression);
        ePropertyType expectedParenthesesArgType = typeDemands.getPropertyType();

        switch (expMethodType) {
            case ENVIRONMENT: //environment(e1)
                methodArgExpression = buildEnvironmentMethodExpression(parenthesesStringValue , expectedParenthesesArgType);
                break;

            case RANDOM: // random(5)
                methodArgExpression = buildRandomMethodArgumentExpression(parenthesesStringValue, expectedParenthesesArgType);
                break;

            case EVALUATE: //evaluate(ent-1.p1)
                methodArgExpression = buildEvaluateMethodArgumentExpression(parenthesesStringValue, expectedParenthesesArgType);
                break;

             case TICKS: // ticks(Sick.vacinated)
                methodArgExpression = buildTicksMethodArgumentExpression(parenthesesStringValue, expectedParenthesesArgType);
                break;

            case PERCENT: // percent(evalutate(ent-2.p1),environment(e1))
                methodArgExpression = buildPercentMethodArgumentExpression(parenthesesStringValue, expectedParenthesesArgType);
                break;

            default:
                throw new WorldBuilderException("Unrecognized method expression, can't build");
        }

        return methodArgExpression;
    }


    @Override
    public EnvironmentMethodArgumentExpression buildEnvironmentMethodExpression(String rawEnvPropString, ePropertyType expectedArgType) {

        EnvironmentMethodArgumentExpression environmentMethodArgumentExpression;

        boolean typeValid =
                contextValidator.validateEnvironemntPropertyTypeAsExpected(
                        rawEnvPropString, expectedArgType);

        boolean envPropertyValid = contextValidator.isEnvironmentProperty(rawEnvPropString);

        if (!envPropertyValid || !typeValid) {
            throw new WorldBuilderException("Cannot build Environemnt method Expression");
        }

       environmentMethodArgumentExpression = new EnvironmentMethodArgumentExpression(
                eExpressionMethod.ENVIRONMENT,
                contextValidator.getEnvironmentPropertyType(rawEnvPropString), //Should return as String
                rawEnvPropString

       );


        return environmentMethodArgumentExpression;
    }

    @Override
    public RandomMethodArgumentExpression buildRandomMethodArgumentExpression(String parenthesesStringValue, ePropertyType expectedArgType) {
        RandomMethodArgumentExpression randomMethodArgumentExpression;
        
        Integer maxRandVal = Integer.parseInt(parenthesesStringValue);
        return new RandomMethodArgumentExpression(
                eExpressionMethod.RANDOM,
                ePropertyType.DECIMAL, // Maybe as a float?
                (new Random()).nextInt(maxRandVal)
        );
    }

    @Override
    public EvaluateMethodArgumentExpression buildEvaluateMethodArgumentExpression(
            String parenthesesStringValue, ePropertyType expectedArgType) {

        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, eParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, eParenthesesPart.RIGHT);

        boolean entityAndPropertyContextValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propTypeAsExpected =
                contextValidator.validateEntityPropertyAsExpected(
                        entityName, propertyName, expectedArgType
                );


        if (!entityAndPropertyContextValid || !propTypeAsExpected) {
            throw new WorldBuilderException(
                    "evaluate Method couldn't be build because entity or property context is incorrect.");
        }


        return new EvaluateMethodArgumentExpression(
                eExpressionMethod.EVALUATE,
                contextValidator.getEntityPropertyType(entityName, propertyName),
                entityName,
                propertyName
        );
    }

    @Override
    public TicksMethodArgumentExpression buildTicksMethodArgumentExpression(String parenthesesStringValue, ePropertyType expectedArgType) {
        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, eParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, eParenthesesPart.RIGHT);

        boolean entityAndPropertyValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propertyTypeAsExpected =
                contextValidator.validateEntityPropertyAsExpected(
                        entityName, propertyName, expectedArgType
                );

        if (!entityAndPropertyValid || !propertyTypeAsExpected) {
            throw new WorldBuilderException(
                    "ticks Method couldn't be build because entity or property context is incorrect.");
        }

        return new TicksMethodArgumentExpression(
                eExpressionMethod.TICKS,
                contextValidator.getEntityPropertyType(entityName, propertyName),
                entityName,
                propertyName);
    }

    @Override
    public PercentMethodArgumentExpression buildPercentMethodArgumentExpression(String parenthesesStringValue, ePropertyType expectedArgType) {

        String rawOriginalValueString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, eParenthesesPart.LEFT);
        String rawPercentageString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, eParenthesesPart.RIGHT);


        ArgumentExpression originalValueExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawOriginalValueString,
                                new ArgExpressionTypeDemands(eMandatoryTypeDemanding.NUMERIC)
                        );
        ArgumentExpression percentageExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawPercentageString,
                                new ArgExpressionTypeDemands(eMandatoryTypeDemanding.NUMERIC)
                        );

        return new PercentMethodArgumentExpression(
                eExpressionMethod.PERCENT,
                ePropertyType.FLOAT,
                originalValueExpression,
                percentageExpression);
    }

    private String extractSubStringInParenthesesString(
            String parenthesesStringValue, char delimiter, eParenthesesPart parenthesesPart) {

        String extractedPart;

        if(parenthesesPart == eParenthesesPart.LEFT) {

            extractedPart = parenthesesStringValue.substring(
                    0, parenthesesStringValue.lastIndexOf(delimiter));

        } else if (parenthesesPart == eParenthesesPart.RIGHT) {

            extractedPart = parenthesesStringValue.substring(
                    parenthesesStringValue.indexOf(delimiter) + 1 ,
                    parenthesesStringValue.length() + 1);

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

        return new PropertyNameArgumentExpression(
                entityPropertyName,
                contextValidator.getEntityPropertyTypeWithoutEntityNameMentioned(entityPropertyName)
        );
    }

    @Override
    public SimpleValueArgumentExpression buildValueArgumentExpression(
            String rawExpression, ArgExpressionTypeDemands typeDemands) {

        SimpleValueArgumentExpression simpleValueExpression;
        ePropertyType propType = typeDemands.getPropertyType();

        switch (propType) {
            case BOOLEAN:
                Boolean booleanValue = Boolean.parseBoolean(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(booleanValue ,ePropertyType.BOOLEAN);
                break;
            case STRING:
                String stringValue = rawExpression;
                simpleValueExpression = new SimpleValueArgumentExpression(stringValue, ePropertyType.STRING);

                break;
            case FLOAT:
                Float floatValue = Float.parseFloat(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(floatValue, ePropertyType.FLOAT);

                break;
            case DECIMAL:
                Integer intValue = Integer.parseInt(rawExpression);
                simpleValueExpression = new SimpleValueArgumentExpression(intValue, ePropertyType.DECIMAL);
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
    public Optional<eExpressionMethod> tryGetMethodType(
            String rawExpression, ArgExpressionTypeDemands typeDemands) {

        eExpressionMethod method = null;

        if (rawExpression.startsWith("environment")){
            method = eExpressionMethod.ENVIRONMENT;
        } else if (rawExpression.startsWith("random")) {
            method = eExpressionMethod.RANDOM;
        } else if (rawExpression.startsWith("evaluate")) {
            method = eExpressionMethod.EVALUATE;
        } else if (rawExpression.startsWith("percent")) {
            method = eExpressionMethod.PERCENT;
        } else if (rawExpression.startsWith("ticks")) {
            method = eExpressionMethod.TICKS;
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
