package simulator.builder.impl.baseImpl;

import simulator.builder.api.interfaces.ArgumentExpressionBuilder;
import simulator.builder.utils.ArgExpressionContextDemands;
import simulator.builder.utils.MandatoryTypeDemanding;
import simulator.builder.utils.ExtractedParenthesesPart;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import enums.PropertyType;
import simulator.definition.rule.action.expression.argumentExpression.api.abstracts.AbstractMethodArgumentExpression;
import simulator.definition.rule.action.expression.argumentExpression.impl.method.*;
import simulator.definition.rule.action.expression.argumentExpression.impl.property.EntityPropertyArgumentExpression;
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
    public ArgumentExpression buildExpression(String rawExpression, ArgExpressionContextDemands expressionContextDemands) {

        ArgumentExpression argExpression;

        Optional<ExpressionMethodType> maybeExpressionMethodType = tryGetMethodType(rawExpression, expressionContextDemands);
        if (maybeExpressionMethodType.isPresent()) {

            argExpression = buildMethodArgumentExpression(
                    maybeExpressionMethodType.get(), rawExpression, expressionContextDemands);

        } else {

            Optional<String> maybeEntityPropertyName = tryGetEntityPropertyName(rawExpression, expressionContextDemands);
            if (maybeEntityPropertyName.isPresent()) {
                argExpression = buildPropertyNameArgumentExpression(maybeEntityPropertyName.get(), expressionContextDemands);
            }
            else {
                // Here we're trying to build ValueArgExpression
                argExpression = buildValueArgumentExpression(rawExpression, expressionContextDemands);
            }
        }

        return argExpression;
    }

    @Override
    public AbstractMethodArgumentExpression buildMethodArgumentExpression(
            ExpressionMethodType expMethodType, String rawExpression, ArgExpressionContextDemands expressionContextDemands) {

        AbstractMethodArgumentExpression methodArgExpression;
        String parenthesesStringValue = extractParenthesesString(rawExpression);

        switch (expMethodType) {
            case ENVIRONMENT: //environment(e1)
                methodArgExpression = buildEnvironmentMethodExpression(parenthesesStringValue , expressionContextDemands);
                break;

            case RANDOM: // random(5)
                methodArgExpression = buildRandomMethodArgumentExpression(parenthesesStringValue, expressionContextDemands);
                break;

            case EVALUATE: //evaluate(ent-1.p1)
                methodArgExpression = buildEvaluateMethodArgumentExpression(parenthesesStringValue, expressionContextDemands);
                break;

             case TICKS: // ticks(Sick.vacinated)
                methodArgExpression = buildTicksMethodArgumentExpression(parenthesesStringValue, expressionContextDemands);
                break;

            case PERCENT: // percent(evalutate(ent-2.p1),environment(e1))
                methodArgExpression = buildPercentMethodArgumentExpression(parenthesesStringValue, expressionContextDemands);
                break;

            default:
                throw new WorldBuilderException("Unrecognized method expression, can't build");
        }

        return methodArgExpression;
    }


    @Override
    public EnvironmentMethodArgumentExpression buildEnvironmentMethodExpression(String rawEnvPropString, ArgExpressionContextDemands expressionContextDemands) {

        EnvironmentMethodArgumentExpression environmentMethodArgumentExpression;

        boolean typeValid =
                contextValidator.validateEnvironemntPropertyTypeAsExpected(
                        rawEnvPropString, expressionContextDemands.getPropertyType());

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
    public RandomMethodArgumentExpression buildRandomMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands) {
        
        Integer maxRandVal = Integer.parseInt(parenthesesStringValue);
        return new RandomMethodArgumentExpression(
                ExpressionMethodType.RANDOM,
                PropertyType.DECIMAL, // Maybe as a float?
                (new Random()).nextInt(maxRandVal)
        );
    }

    @Override
    public EvaluateMethodArgumentExpression buildEvaluateMethodArgumentExpression(
            String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands) {

        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.RIGHT);

        boolean entityAndPropertyContextValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propTypeAsExpected;
        if (expressionContextDemands.getMandatoryDemand() == MandatoryTypeDemanding.Mentioned) {
                propTypeAsExpected = contextValidator.validateEntityPropertyAsExpected(
                        entityName, propertyName, expressionContextDemands.getPropertyType()
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
    public TicksMethodArgumentExpression buildTicksMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands) {
        String entityName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.LEFT);
        String propertyName =
                extractSubStringInParenthesesString(parenthesesStringValue, PERIOD, ExtractedParenthesesPart.RIGHT);

        boolean entityAndPropertyValid =
                contextValidator.validateActionContextProcedure(entityName, propertyName);

        boolean propTypeAsExpected;
        if (expressionContextDemands.getMandatoryDemand() != MandatoryTypeDemanding.NotMentioned) {
            propTypeAsExpected = contextValidator.validateEntityPropertyAsExpected(
                    entityName, propertyName, expressionContextDemands.getPropertyType()
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
    public PercentMethodArgumentExpression buildPercentMethodArgumentExpression(String parenthesesStringValue, ArgExpressionContextDemands expressionContextDemands) {

        String rawOriginalValueString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, ExtractedParenthesesPart.LEFT);
        String rawPercentageString =
                extractSubStringInParenthesesString(parenthesesStringValue, COMMA, ExtractedParenthesesPart.RIGHT);


        ArgumentExpression originalValueExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawOriginalValueString,
                                new ArgExpressionContextDemands(
                                        PropertyType.FLOAT
                                )
                        );

        ArgumentExpression percentageExpression =
                new BaseArgumentExpressionBuilder(contextValidator)
                        .buildExpression(
                                rawPercentageString,
                                new ArgExpressionContextDemands(
                                        PropertyType.FLOAT
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
    public EntityPropertyArgumentExpression buildPropertyNameArgumentExpression(String entityPropertyName, ArgExpressionContextDemands expressionContextDemands) {

        String entityName;
        if (expressionContextDemands.getEntityName().isPresent()) {
            entityName = expressionContextDemands.getEntityName().get();
        } else {
            throw new WorldBuilderException("entityName doesn't provided in the context of property arg expression build.");
        }

        boolean entityAndPropertyValid = contextValidator.validateActionContextProcedure(entityName, entityPropertyName);
        if (!entityAndPropertyValid) {
            throw new WorldBuilderException("entity name - property name context is invalid. can't build property arg expression");
        }

        return new EntityPropertyArgumentExpression(
                entityName,
                entityPropertyName,
                contextValidator.getEntityPropertyTypeWithoutEntityNameMentioned(entityPropertyName)
        );

    }

    @Override
    public SimpleValueArgumentExpression buildValueArgumentExpression(
            String rawExpression, ArgExpressionContextDemands expressionContextDemands) {

        SimpleValueArgumentExpression simpleValueExpression;
        PropertyType propType = expressionContextDemands.getPropertyType();

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
            String rawExpression, ArgExpressionContextDemands expressionContextDemands) {

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
            String rawExpression, ArgExpressionContextDemands expressionContextDemands) {

        String entityPropertyName = null;
        boolean entityPropertyNameExist = contextValidator.isEntityPropertyNameExists(rawExpression);

        if (entityPropertyNameExist) {
            entityPropertyName = rawExpression;
        }

        return Optional.ofNullable(entityPropertyName);
    }

}
