package simulator.information.simulationDocument.api;

import java.util.List;
import java.util.Map;

public interface SimulationDocumentFacade {

    Map<String, Integer> getMappedEntitiesToNumOfEntities();
    Map<String, Integer> getMappedPropertiesToNumOfEntitiesWithSameValues(String propertyName);
    List<String> getAllEntitiesNames();
}
