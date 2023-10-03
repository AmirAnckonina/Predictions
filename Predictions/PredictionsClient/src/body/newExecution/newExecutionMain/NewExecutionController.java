package body.newExecution.newExecutionMain;


import body.newExecution.components.entityPopulation.EntityPopulationController;
import body.newExecution.components.environmentVariable.KeyValueProperty;
import body.newExecution.components.environmentVariable.bool.EnvironmentBooleanVariableController;
import body.newExecution.components.environmentVariable.floats.EnvironmentFloatVariableController;
import body.newExecution.components.environmentVariable.string.EnvironmentStringVariableController;
import com.google.gson.reflect.TypeToken;
import dto.worldBuilder.simulationComponents.EnvironmentPropertyDto;
import dto.simulationInfo.SimulationDocumentInfoDto;
import dto.manualSetup.SimulationManualParamsDto;
import enums.SetPropertyStatus;
import exception.PredictionsUIComponentException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.PredictionsMainController;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import simulator.mainManager.api.SimulatorManager;
import utils.HttpClientUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.CommonResourcesPaths.*;
import static utils.Constants.*;

public class NewExecutionController {

    @FXML private ListView<GridPane> entPopulationListView;
    @FXML private ListView<GridPane> envPropListView;
    @FXML private Button clearVarButton;
    @FXML private Button startButton;
    @FXML private Label maxPopLabel;
    @FXML private ProgressIndicator loadingProgressIndicator;
    private Map<String, EntityPopulationController> entityPopulationControllerMap;
    private Map<String, KeyValueProperty> environmentPropertyControllerMap;
    private PredictionsMainController predictionsMainController;
    private Stage primaryStage;
    private boolean firstSimulationStartedFlag;

    private SimulatorManager simulatorManager;

    public NewExecutionController() {
        this.entityPopulationControllerMap = new HashMap<>();
        this.environmentPropertyControllerMap = new HashMap<>();
        firstSimulationStartedFlag = false;
    }

    @FXML
    private void initialize() {
    }

    public void setPredictionsMainController(PredictionsMainController predictionsMainController) {
        this.predictionsMainController = predictionsMainController;
    }

    @FXML
    void onClearVarClicked() {
        entityPopulationControllerMap.forEach((entityName, controller) -> controller.clearAndResetProperty());
        environmentPropertyControllerMap.forEach((envPropName, controller) -> controller.clearAndResetProperty());
    }

    @FXML
    void onStartButtonClicked() {

        try {

            SimulationDocumentInfoDto simulationDocumentInfoDto = this.simulatorManager.runSimulator("");
            if (!firstSimulationStartedFlag) {
                firstSimulationStartedFlag = true;
                this.predictionsMainController.onFirstSimulationStarted();
            }
            this.predictionsMainController.onNewSimulationStart(simulationDocumentInfoDto.getSimulationGuid());
            predictionsMainController.resultsTabClicked();

        } catch (Exception e) {
            e.printStackTrace(System.out);

        } finally {
            this.simulatorManager.resetAllManualSetup("");
            resetController();
        }

    }

    public void initializeNewExecutionTab(String simulationWorldNameToLoad) {

        try {
            resetController();
            getMaxPopulationSizeProcedure(simulationWorldNameToLoad);
            getAllEntitiesProcedure(simulationWorldNameToLoad);
            getAllEnvironmentPropertiesProcedure(simulationWorldNameToLoad);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void getAllEnvironmentPropertiesProcedure(String simulationWorldNameToLoad) {

        String finalUrl =
                HttpUrl
                        .parse(GET_ALL_ENV_PROPERTIES_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(SIMULATION_WORLD_NAME_PARAM_KEY, simulationWorldNameToLoad)
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type typeToDeserialize = new TypeToken<ArrayList<EnvironmentPropertyDto>>(){}.getType();
                    List<EnvironmentPropertyDto> environmentPropertyDtoList = GSON_INSTANCE.fromJson(responseBody, typeToDeserialize);
                    loadEnvironmentProperitesComponents(environmentPropertyDtoList);
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getAllEnvironemtPropertiesProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }
        });
    }

    private void loadEnvironmentProperitesComponents(List<EnvironmentPropertyDto> environmentPropertyDtoList) {
        environmentPropertyDtoList.forEach(this::createEnvironmentPropertyComponent);
    }

    private void getAllEntitiesProcedure(String simulationWorldNameToLoad) {
        String finalUrl =
                HttpUrl
                        .parse(GET_ALL_ENTITIES_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(SIMULATION_WORLD_NAME_PARAM_KEY, simulationWorldNameToLoad)
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Type typeToDeserialize = new TypeToken<ArrayList<String>>(){}.getType();
                    List<String> entitiesNames = GSON_INSTANCE.fromJson(responseBody, typeToDeserialize);
                    loadEntitiesComponents(entitiesNames);
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getAllEntitiesProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }
        });

    }

    private void loadEntitiesComponents(List<String> entitiesNames) {
        entitiesNames.forEach(this::createEntityPopulationComponent);
    }

    private void getMaxPopulationSizeProcedure(String simulationWorldNameToLoad) {
        String finalUrl =
                HttpUrl
                        .parse(GET_MAX_POPULATION_ENDPOINT)
                        .newBuilder()
                        .addQueryParameter(SIMULATION_WORLD_NAME_PARAM_KEY, simulationWorldNameToLoad)
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                   Integer maxPopulationValue = GSON_INSTANCE.fromJson(responseBody, Integer.class);
                   loadMaxPopulationValue(maxPopulationValue);
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getAllEntitiesProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }
        });

    }

    private void loadMaxPopulationValue(Integer maxPopulationValue) {
        Platform.runLater(() ->  this.maxPopLabel.textProperty().set("   MAX POPULATION: " + " " + maxPopulationValue));

    }


    private void resetController() {
        this.entPopulationListView.getItems().clear();
        this.envPropListView.getItems().clear();
        this.entityPopulationControllerMap = new HashMap<>();
        this.environmentPropertyControllerMap = new HashMap<>();
    }

    private void createEnvironmentPropertyComponent(EnvironmentPropertyDto envProp) {

        switch (envProp.getPropertyType()) {
            case STRING:
                createEnvironmentStringVariableComponent(envProp);
                break;
            case FLOAT:
            case DECIMAL:
                createEnvironmentFloatVariableComponent(envProp);
                break;
            case BOOLEAN:
                createEnvironmentBooleanVariableComponent(envProp);
                break;
            default:
                throw new PredictionsUIComponentException("Can't create GridPane for key-value");
        }
    }

    private void createEnvironmentBooleanVariableComponent(EnvironmentPropertyDto envProp) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_BOOLEAN_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentBooleanVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvBooleanVariable(envProp.getEnvPropName());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }
    }

    private void createEnvironmentFloatVariableComponent(EnvironmentPropertyDto envProp) {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_FLOAT_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentFloatVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvFloatVariable(envProp.getEnvPropName(), envProp.getRange());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }

    }

    private void createEnvironmentStringVariableComponent(EnvironmentPropertyDto envProp) {

        try
        {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENV_STRING_VAR_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EnvironmentStringVariableController controller = loader.getController();
            controller.setNewExecutionController(this);
            controller.initSetupForEnvStringVariable(envProp.getEnvPropName());
            envPropListView.getItems().add((GridPane) gpComponent);
            environmentPropertyControllerMap.put(envProp.getEnvPropName(), controller);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
        }

    }

    private void createEntityPopulationComponent(String entityName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(ENTITY_POPULATION_FXML_RESOURCE);
            loader.setLocation(fxmlUrl);
            Node gpComponent = loader.load();

            EntityPopulationController entityPopulationController = loader.getController();
            entityPopulationController.setNewExecutionController(this);
            entityPopulationController.initSetupForEntityPopulation(entityName);
            entPopulationListView.getItems().add((GridPane) gpComponent);
            entityPopulationControllerMap.put(entityName, entityPopulationController);
        } catch (IOException ioe) {
//            System.out.println(ioe.getMessage());
            ioe.printStackTrace(System.out);
//            System.out.println(ioe.getMessage());
            //throw new PredictionsUIComponentException("failed to load component under GridPaneFactory.");
        } catch (Exception e) {
//            e.getMessage();
            e.printStackTrace(System.out);
//            System.out.println(e.getMessage());
        }
    }

    public void setSingleEntityPopulation(String entityName, Integer population) {

        try {
             this.simulatorManager.setEntityDefinitionPopulation("",entityName, population);
             this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.SUCCEEDED);
        } catch (Exception e) {
            this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.FAILED);
        }
    }

    public <T> void setEnvironmentProperty(String envPropertyName, T envPropertyValue) {
        try {
            this.simulatorManager.setEnvironmentPropertyValue("", envPropertyName, envPropertyValue);
            this.environmentPropertyControllerMap.get(envPropertyName).setStatus(SetPropertyStatus.SUCCEEDED);
        } catch (Exception e) {
            this.environmentPropertyControllerMap.get(envPropertyName).setStatus(SetPropertyStatus.FAILED);
        }
    }

    public void setSimulatorManager(SimulatorManager simulatorManager) {
        this.simulatorManager = simulatorManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void rollbackSingleEntityPopulation(String entityName) {
        this.simulatorManager.resetSingleEntityPopulation("", entityName);
        this.entityPopulationControllerMap.get(entityName).setStatus(SetPropertyStatus.NONE);
    }

    public void rollbackSingleEnvironmentVariable(String envVarName) {
        this.simulatorManager.resetSingleEnvironmentVariable("", envVarName);
        this.environmentPropertyControllerMap.get(envVarName).setStatus(SetPropertyStatus.NONE);
    }

    public void reset() {
    }

    public void setNewExecutionTabToRerunSimulation(String simulationGuid) {
        try {
            SimulationManualParamsDto simulationManualParamsDto = this.simulatorManager.getSimulationManualParamsByGuid(simulationGuid);
            this.initializeNewExecutionTab("fakesimulationname");
            this.entityPopulationControllerMap
                    .forEach(
                            (entityName, controller) ->
                                    controller.setPropertyValueByManualParamProcedure(
                                            simulationManualParamsDto.getEntityDefinitionPopulationMap().get(entityName)));


            this.environmentPropertyControllerMap
                    .forEach(
                            (envPropName, controller) ->
                                    controller.setPropertyValueByManualParamProcedure(
                                            simulationManualParamsDto.getEnvironmentPropertyMap().get(envPropName)));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
