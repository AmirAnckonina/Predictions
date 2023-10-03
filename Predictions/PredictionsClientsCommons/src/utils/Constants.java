package utils;

import com.google.gson.Gson;

public class Constants {

    // Gson instance
    public final static Gson GSON_INSTANCE = new Gson();
    public final static int SIMULATION_WORLD_LIST_REFRESH_RATE = 2000;
    public final static int SIMULATION_REQUESTS_LIST_REFRESH_RATE = 2000;


    // Server resources locations
    public final static String BASE_DOMAIN = "localhost";
    private final static String BASE_URL = "http://" + BASE_DOMAIN + ":8080";
    private final static String CONTEXT_PATH = "/Web-Predictions";
    private final static String FULL_SERVER_PATH = BASE_URL + CONTEXT_PATH;

    // ENDPOINTS + PARAM KEYS
    public final static String GET_SIMULATION_WORLD_NAMES_LIST_ENDPOINT = FULL_SERVER_PATH + "/worldBuilder/simulationWorldNamesList";
    //
    public final static String GET_SIMULATION_WORLD_DETAILS_ENDPOINT = FULL_SERVER_PATH + "/worldBuilder/simulationWorldDetails";
    public final static String GET_SIMULATION_WORLD_NAME_PARAM_KEY = "simulationWorldName";
    //
    public final static String GET_ENVIRONMENT_PROPERTIES_DEFINITION_ENDPOINT = FULL_SERVER_PATH + "/worldBuilder/environmentPropertiesDefinition";
    //
    public final static String POST_NEW_SIMULATION_LOAD_ENDPOINT = FULL_SERVER_PATH + "/worldBuilder/loadSimulationWorldFile";
    public final static String POST_NEW_SIMULATION_REQUEST_ENDPOINT = FULL_SERVER_PATH + "/simulationRequests/newSimulationRequest";
    public final static String POST_UPDATE_SIMULATION_REQUEST_ENDPOINT = FULL_SERVER_PATH + "/simulationRequests/updateSimulationRequest";
    public final static String POST_NEW_SIMULATION_REQUEST_PARAM_KEY = "simulationRequestBody";
    //
    public final static String GET_SIMULATION_REQUESTS_LIST_ENDPOINT = FULL_SERVER_PATH + "/simulationRequest/simulationRequestsList";
    //

    public final static String GET_ALL_ENV_PROPERTIES_ENDPOINT = FULL_SERVER_PATH + "/manualSetup/allEnvProperties";
    public final static String GET_ALL_ENTITIES_ENDPOINT = FULL_SERVER_PATH + "/manualSetup/allEntities";
    public final static String GET_MAX_POPULATION_ENDPOINT = FULL_SERVER_PATH + "/manualSetup/maxPopulation";



}
