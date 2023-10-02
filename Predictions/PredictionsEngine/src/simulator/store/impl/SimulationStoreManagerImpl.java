package simulator.store.impl;

import simulator.store.api.SimulationStoreManager;
import simulator.store.orderRequest.api.SimulationOrderRequest;

import java.util.Map;

public class SimulationStoreManagerImpl implements SimulationStoreManager {
    Map<String, SimulationOrderRequest> simulationOrderRequestMap;

}
