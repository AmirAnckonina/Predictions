package UI.impl;

import UI.api.UserInterface;
import UI.utils.enums.eMainMenuChoices;
import UI.utils.enums.ePresentShowOptions;
import dto.*;
import response.SimulatorResponse;
import simulator.execution.instance.entity.impl.EntitiesResult;
import simulator.result.api.SimulationResult;
import simulator.manager.api.SimulatorManager;
import simulator.result.manager.api.SimulatorResultManager;
import simulator.manager.impl.SimulatorManagerImpl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

public class ConsoleUI implements UserInterface {

    private SimulatorManager simulatorManager;
    private SimulatorResultManager simulatorResultManager;
    private boolean endSessionFlag = false;
    private Integer simulationIndex = 0;

    public ConsoleUI() {

        this.simulatorManager = new SimulatorManagerImpl();
        this.simulatorResultManager = this.simulatorManager.getSimulatorResultManagerImpl();
    }

    @Override
    public void runSimulatorUI() {

        try {
            runConsoleMenu();
        } catch (Exception e) {
            System.out.println("An error detected: " + e.getMessage() + "\n" + "Would you like to restart or quit? (y/n)");
            if (yesNoSession()) {
                runSimulatorUI();
            } else {
            }
        }
        System.out.println("Goodbye!");
    }

    @Override
    public void loadSimulationSession() {
        startLoadingSimulationSessionSignal();
        while (true) {
            printLoadingSimulationMenu();
            String simulationFilePath = handleLoadingSimulationUserChoice();
            //String simulationFilePath = "PredictionsEngine/src/resources/ex1-cigarets.xml";
            //String simulationFilePath = "PredictionsEngine/src/resources/ex1-error-6.xml";
            SimulatorResponse response = simulatorManager.buildSimulationWorld(simulationFilePath);
            if(response.isSuccess()){
                System.out.println(response.getMessage());
                break;
            }
            System.out.println("The data source file is invalid.");
        }

        endLoadingSimulationSessionSignal();
    }

    @Override
    public void showLoadedSimulationWorldDetails() {
        SimulatorResponse<SimulationDetailsDto> response = simulatorManager.getSimulationWorldDetails();
        if (response.isSuccess()) {
            System.out.println(response.getData().getInfo());
        }
        else {
            System.out.println(response.getMessage());
        }
    }

    @Override
    public void runSimulationSession() {

        setEnvironmentPropertiesValues();
        SimulatorResponse response = simulatorManager.establishSimulation();
        if (response.isSuccess()) {
            runSimulationSessionForEstablishedEnvironment();
        } else {
            System.out.println(response.getMessage());
        }

    }

    private void announceEndSimulation() {
        System.out.println("Simulation ended!");
        System.out.println("Unique simulation ID: - " + this.simulatorResultManager
                .getSimulatorIDByIndex(this.simulatorResultManager.getSortedHistoricalSimulationsList().size() - 1) + "\n");
    }

    private void establishSimulationSession() {


    }

    @Override
    public void showHistoricalSimulationResult() {

    }

    @Override
    public void exitSimulator() {

    }

    private void runConsoleMenu() {

        while (!this.endSessionFlag) {
            printMenuOptions();
            mapUserChoiceToAction(handleMainMenuUserChoice());
        }
    }

    private void printMenuOptions() {
        System.out.println(((int) eMainMenuChoices.LoadSimulation.ordinal() + 1) + ". Load simulation");
        System.out.println(((int) eMainMenuChoices.ShowLoadedSimulationWorldDetails.ordinal() + 1) + ". Show loaded simulation world details");
        System.out.println(((int) eMainMenuChoices.RunSimulation.ordinal() + 1) + ". Run loaded simulation");
        System.out.println(((int) eMainMenuChoices.GetHistoricalSimulationDetails.ordinal() + 1) + ". Get historical simulation results");
        System.out.println(((int) eMainMenuChoices.Exit.ordinal() + 1) + ". Exit");

        System.out.print("Choose an option by typing the option number: ");
    }

    private int handleMainMenuUserChoice(){
        Scanner scanner = new Scanner(System.in);
        int selectedIndex = -1;

        while (true) {
            if (scanner.hasNextInt()) {
                selectedIndex = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (selectedIndex >= 1 && selectedIndex <= eMainMenuChoices.values().length) {
                    break;
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid index.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return selectedIndex - 1;
    }

    private void mapUserChoiceToAction(int userChoice) {

        switch (eMainMenuChoices.values()[userChoice]) {
            case LoadSimulation:
                loadSimulationSession();
                break;
            case ShowLoadedSimulationWorldDetails:
                showLoadedSimulationWorldDetails();
                break;
            case RunSimulation:
                runSimulationSession();
                break;
            case GetHistoricalSimulationDetails:
                getHistoricalSimulationDetails();
                break;
            case Exit:
                this.endSessionFlag = true;
        }
    }

    private void getHistoricalSimulationDetails() {
        List<SimulationResult> simulationResults = this.simulatorResultManager.getSortedHistoricalSimulationsList();

        printHistoricalSimulationList(simulationResults);
        handleUserSimulationResultChoice(simulationResults);
    }

    private void printHistoricalSimulationList(List<SimulationResult> simulationResults) {
        for (int i = 0; i < simulationResults.size(); i++) {

            System.out.println((i + 1) + ". SimulationUuid:\"" + simulationResults.get(i).getSimulationUuid() + "\"" + ": "
                    + getSimulatorStartingTimeInString(simulationResults.get(i)));
        }
        System.out.print("Choose simulation to present: ");
    }

    private void runSimulationSessionForEstablishedEnvironment() {
        showEstablishedEnvironmentInfo();
        SimulatorResponse<SimulationEndDto> result = simulatorManager.runSimulator();
        if (result.isSuccess()) {
            // print the Guid and the termination reason
        } else {
            System.out.println(result.getMessage());
        }
    }

    private void showEstablishedEnvironmentInfo() {
        SimulatorResponse<EstablishedEnvironmentInfoDto> environmentInfoResponse =
                simulatorManager.getEstablishedEnvironmentInfo();

        if (environmentInfoResponse.isSuccess()) {
            System.out.println("Environment established with the following properties values: ");
            Map<String, String> envPropertiesInfo =
                    environmentInfoResponse.getData().getEstablishedEnvironmentProperties();

            for (Map.Entry<String, String> envProp : envPropertiesInfo.entrySet()) {
                System.out.println(envProp.getKey() + " = " + envProp.getValue());
            }

        } else {
            environmentInfoResponse.getMessage();
        }
    }

    private void printEnvironmentPropertiesValues(List<BasePropertyDto> properties) {
        for (int i = 0; i < properties.size(); i++) {
            BasePropertyDto property = properties.get(i);
            System.out.println((i + 1) + ". " + property.getName() + ": " + property.getValue());
        }
    }

    private void endLoadingSimulationSessionSignal() {
        simulatorManager.endLoadingSimulationSessionSignal();
    }

    private String handleLoadingSimulationUserChoice() {
        String filePath;
        Scanner scanner = new Scanner(System.in);
        boolean isValidFile;
        filePath = scanner.nextLine();
//        do {
//            File file = new File(filePath);
//            isValidFile = file.exists() && !file.isDirectory();
//        } while (!isValidFile);

        return filePath;
    }

    private void printLoadingSimulationMenu() {
        System.out.println("Inset the full path of the XML file and press enter:");
    }

    private void startLoadingSimulationSessionSignal() {

        simulatorManager.startLoadingSimulationSessionSignal();
    }

    private void setEnvironmentPropertiesValues() {
        startEnvironmentSessionSignal();
        SimulatorResponse<EnvironmentPropertiesDto>
                propertiesDtoResponse = simulatorManager.getEnvironmentPropertiesDefinition();
        if (propertiesDtoResponse.isSuccess()) {
            EnvironmentPropertiesDto propetiesDto = propertiesDtoResponse.getData();
            List<BasePropertyDto> properties = propetiesDto.getPropertiesList();
            List<Integer> propertiesUserUpdatedList = handleUserPropertyChoice(properties);
            endEnvironmentSessionSignal();
        } else {
            System.out.println(propertiesDtoResponse.getMessage());
        }
    }

    private boolean startEnvironmentSessionSignal(){
        return this.simulatorManager.startEnvironmentSession().isSuccess();
    }

    private boolean endEnvironmentSessionSignal(){
        return this.simulatorManager.endEnvironmentSession().isSuccess();
    }

    private void printPropertiesList(List<BasePropertyDto> propertyDtoList){
        for (int i = 0; i < propertyDtoList.size(); i++) {
            System.out.println((i + 1) + ". \"" + propertyDtoList.get(i).getName() + "\"");
        }
    }

    private void handleUserSimulationResultChoice(List<SimulationResult> simulationResults) {
        if(simulationResults.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNextInt()) {
                    int selectedIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (selectedIndex >= 1 && selectedIndex <= simulationResults.size()) {
                        this.simulationIndex = selectedIndex - 1;
                        showHowToPresentResultMenu();
                        ePresentShowOptions showOption = howToPresentResultMenuChoiceHandler();
                        showOptionPresenter(showOption);
                        break;

                    } else {
                        System.out.println("Invalid index. Please choose a valid index.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid index.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        }else {
            System.out.println("There is no simulation History\nReturn to main menu\n");
        }
    }

    private void showOptionPresenter(ePresentShowOptions showOption) {
        switch (showOption) {
            case ByAmount:
                 List<EntitiesResult> entitiesResultList =
                         this.simulatorResultManager.getAllEntitiesExistBySimulationIndex(this.simulationIndex);

                 printHowManyEntitiesInstancesExist(entitiesResultList);
                break;

            case ByProperty:
                String propertiesName = new String();
                List<String> propertiesNames = this.simulatorResultManager.getAllPropertiesOfEntityBySimulationIndex(this.simulationIndex);
                printPropertiesListAfterSimulation(propertiesNames);
                String propertyNameChosen = propertyNameChosenForPresentResultHandler(propertiesNames);
                Map<String,Integer> entityInstanceList = this.simulatorResultManager
                        .getAllEntitiesHasPropertyByPropertyNameBySimulationIndex(this.simulationIndex, propertyNameChosen);
                showAllEntitiesHavePropertyByPropertyName(entityInstanceList);
                break;
        }
    }

    private String propertyNameChosenForPresentResultHandler(List<String> propertiesNames) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (scanner.hasNextInt()) {
                int selectedIndex = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (selectedIndex >= 1 && selectedIndex <= propertiesNames.size()) {
                    return propertiesNames.get(selectedIndex - 1);
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid index.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private void showAllEntitiesHavePropertyByPropertyName(Map<String,Integer> entityInstanceList) {
        int index = 1;
        for (Map.Entry<String,Integer> entry : entityInstanceList.entrySet()) {
            String instanceValue = entry.getKey();
            Integer numOfInstances = entry.getValue();
            if(numOfInstances == 1){
                System.out.println( index+ ". There is " + numOfInstances + " instance with the value " + instanceValue + "\n");
            }else {
                System.out.println(index + ". There are " + numOfInstances + " instances with the value " + instanceValue + "\n");
            }

            index++;
        }
        if(entityInstanceList.size() == 0){
            System.out.println("There are no instances with this property\n");

        }
    }

    private void printHowManyEntitiesInstancesExist(List<EntitiesResult> entitiesResultList) {
        EntitiesResult entitiesResult = entitiesResultList.get(0);
        Integer i = 0;
        String entityName = (entitiesResult.getName() != null)?String.valueOf(entitiesResult.getName()):"primary";
        System.out.println((i + 1) + ". " + "number of " + entityName + " entity instances in the end of the simulation: " + " "
                + entitiesResult.getNumOfInstancesInEndOfSimulation()
                + "/"
                + entitiesResult.getNumOfInstancesInitialized() + "\n");
    }

    private void printPropertiesListAfterSimulation(List<String> propertiesNamee) {
        for (int i = 0; i < propertiesNamee.size(); i++) {
            System.out.println((i + 1) + ". " + propertiesNamee.get(i));
        }

        System.out.print("Choose property instance to be filter: ");
    }

    private ePresentShowOptions howToPresentResultMenuChoiceHandler() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            if (scanner.hasNextInt()) {
                int selectedIndex = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (selectedIndex >= 1 && selectedIndex <= ePresentShowOptions.values().length) {
                    List<ePresentShowOptions> enumValues = Arrays.asList(ePresentShowOptions.values());
                    return enumValues.get(selectedIndex - 1);
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid index.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private void showHowToPresentResultMenu() {
        System.out.println("How would you like to see the results?");
        System.out.println("1. By amount of entities");
        System.out.println("2. By chosen property");
        System.out.print("Choose an option by typing the option number: ");
    }

    private List<Integer> handleUserPropertyChoice(List<BasePropertyDto> propertyDtoList) {
        System.out.println("Select a property to set a value.");

        List<Integer> propertiesUserUpdatedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String continueChoice = null;
            printPropertiesList(propertyDtoList);
            System.out.print("Do you want to set value for one of the environment properties? (y/n): ");

            // If true return - that meands the user want to insertValues to props.
            if (!yesNoSession()) {
                break;
            }
            System.out.print("Enter the index of the property you would like to update: ");

            if (scanner.hasNextInt()) {
                int selectedIndex = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (selectedIndex >= 1 && selectedIndex <= propertyDtoList.size()) {
                    BasePropertyDto selectedProperty = propertyDtoList.get(selectedIndex - 1);
                    startSettingPropertySession(selectedProperty);
                    propertiesUserUpdatedList.add(selectedIndex - 1);
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid index.");
                scanner.nextLine(); // Consume the invalid input
            }


        }

        return propertiesUserUpdatedList;
    }

    private boolean yesNoSession() {
        String continueChoice;
        Scanner scanner = new Scanner(System.in);
        continueChoice = scanner.nextLine();

        while (!continueChoice.equalsIgnoreCase("n") &&
                !continueChoice.equalsIgnoreCase("y")) {

            System.out.print("\nInvalid input! type 'y' for yes and 'n' for no only: ");
            continueChoice = scanner.nextLine();
        }

        if (continueChoice.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }


    private void startSettingPropertySession(BasePropertyDto property) {
        String propertyName = new String(property.getName());
        String propertyType = new String(property.getPropertyType());
        String propertyRangeString = (property.getFrom() != null && property.getTo() != null)
                ?new String("from: " + property.getFrom() + " to: " + property.getTo()):null;
        Scanner scanner = new Scanner(System.in);

        setPropertyIntroduction();
        setPropertyDisplay(propertyName, propertyType, propertyRangeString);
        String value = scanner.nextLine();
        SimulatorResponse<SetPropertySimulatorResponseDto> resResponse =
                this.simulatorManager.setSelectedEnvironmentVariablesValue(
                        propertyName, propertyType, value);

        while (!resResponse.isSuccess()){
            System.out.println("Invalid value!");
            setPropertyDisplay(propertyName, propertyType, propertyRangeString);
            value = scanner.nextLine();
            resResponse = this.simulatorManager.setSelectedEnvironmentVariablesValue(propertyName, property.getPropertyType(), value);
        }

    }

    private void setPropertyIntroduction(){
        System.out.println("Initializing environment properties...\n");
    }

    private void setPropertyDisplay(String propertyName, String propertyType, String range){
        System.out.println("Property name: " + propertyName);
        System.out.println ((range != null)?"values must be " + range:"");
        switch (propertyType.toLowerCase())
        {
            case "string":
            case "str":
                System.out.println("(insert value and press enter)");
                break;
            case "integer":
            case "int":
                System.out.println("(insert integer numbers only and press enter)");
                break;
            case "boolean":
            case "bool":
                System.out.println("(insert y/n and press enter)");
                break;
            case "float":
            case "decimal":
            case "double":
                System.out.println("(insert numbers only and press enter)");
                break;
        }
    }

    private String getSimulatorStartingTimeInString(SimulationResult simulationResults){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH.mm.ss");
        String formattedTime = formatter.format(new Date(simulationResults.getSimulationStartingTime()));

        return formattedTime;
    }
}
