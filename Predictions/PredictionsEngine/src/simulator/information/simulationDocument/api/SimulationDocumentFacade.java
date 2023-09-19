package simulator.information.simulationDocument.api;

import java.util.Map;

public interface SimulationDocumentFacade {

    Map<String, Integer> getMappedEntitiesToNumOfEntities();
    Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String entityName, String propertyName);
}
