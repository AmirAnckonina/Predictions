package simulator.builder.world.validator.api;

import simulator.builder.world.utils.enums.eDataFileType;
import simulator.definition.rule.action.utils.eActionType;

public interface WorldContextBuilderHelper {
    boolean validateFileExist(String filePath);
    boolean validateFileType(eDataFileType actualDataFileType, eDataFileType expectedDataFileType);
    boolean validateEnvironmentPropertyUniqueness(String envPropertyName);
    boolean validateEntityPropertyUniqueness(String entityName ,String entityPropertyName);
    boolean validateActionEntityContext(String entityName);
    boolean validateActionEntityPropertyContext(String entityName, String entityPropertyName);
    boolean validateActionArguments(eActionType actionType, String... actionArgs);
    void addEnvironemntProperty(String newEnvPropName);
    void addPropertyToEntityProperties(String entity, String entityProperty);
    void addEntity(String entity);
}
