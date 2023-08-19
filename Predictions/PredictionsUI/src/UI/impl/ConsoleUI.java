package UI.impl;

import UI.api.UserInterface;
import dto.EnvironmentPropertiesDto;
import dto.EstablishedEnvironmentInfoDto;
import dto.SetPropertySimulatorResponseDto;
import dto.SimulationDetailsDto;
import dto.builder.params.BasePropertyDto;
import response.SimulatorResponse;
import simulator.manager.api.SimulatorManager;
import simulator.manager.impl.SimulatorManagerImpl;

import java.util.*;
import java.io.File;

public class ConsoleUI implements UserInterface {

    private SimulatorManager simulatorManager;
    private boolean endSessionFlag = false;
    private String simulationID;

    public ConsoleUI() {
        this.simulatorManager = new SimulatorManagerImpl();
    }

    @Override
    public void runSimulatorUI() {
        runConsoleMenu();
    }

    @Override
    public void loadSimulationSession() {
        startLoadingSimulationSessionSignal();
        printLoadingSimulationMenu();
        //String simulationFilePath = handleLoadingSimulationUserChoice();
        String simulationFilePath = "PredictionsEngine/src/resources/ex1-cigarets.xml";
        SimulatorResponse response = simulatorManager.buildSimulationWorld(simulationFilePath);
        System.out.println(response.getMessage());
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
                break;
            case Exit:
                this.endSessionFlag = true;
        }
    }

    private void runSimulationSessionForEstablishedEnvironment() {
        showEstablishedEnvironmentInfo();
        SimulatorResponse<String> result = simulatorManager.runSimulator();
        this.simulationID = result.getData();
    }

    private void showEstablishedEnvironmentInfo() {
        SimulatorResponse<EstablishedEnvironmentInfoDto> environmentInfoResponse =
                simulatorManager.getEstablishedEnvironmentInfo();

        if (environmentInfoResponse.isSuccess()) {
            System.out.println("Environment established with the following properties values: ");
            Map<String, String> envPropertiesInfo =
                    environmentInfoResponse.getData().getEstablishedEnvironmentProperties();

            for (Map.Entry<String, String> envProp : envPropertiesInfo.entrySet()) {
                System.out.println(envProp.getKey() + "=" + envProp.getValue());
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
        do {
            File file = new File(filePath);
            isValidFile = file.exists() && !file.isDirectory();
        } while (!isValidFile);

        return filePath;
    }

    private void printLoadingSimulationMenu() {
        System.out.println("Inset the full path of the XML file and press enter:");

    }

    private void startLoadingSimulationSessionSignal() {

        simulatorManager.startLoadingSimulationSessionSignal();
    }

    private void setEnvironmentPropertiesValues() {
        Boolean doneFillingProperties = false;
        startEnvironmentSessionSignal();
        EnvironmentPropertiesDto propertiesDto = simulatorManager.getEnvironmentProperties();
        List<BasePropertyDto> properties = propertiesDto.getPropertiesList();
        List<Integer> propertiesUserUpdatedList = handleUserPropertyChoice(properties);
        endEnvironmentSessionSignal();
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

    private List<Integer> handleUserPropertyChoice(List<BasePropertyDto> propertyDtoList) {
        System.out.println("Select a property to set a value.");

        List<Integer> propertiesUserUpdatedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printPropertiesList(propertyDtoList);
            System.out.println("Enter the index of the property you would like to update: ");

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
            String continueChoice = null;
            System.out.print("Do you want to update more variables? (y/n): ");
            continueChoice = scanner.nextLine();

            while (!continueChoice.equalsIgnoreCase("n") &&
                    !continueChoice.equalsIgnoreCase("y")) {

                System.out.print("\nInvalid input! type 'y' for yes and 'n' for no only: ");
                continueChoice = scanner.nextLine();
            }

            if (!continueChoice.equalsIgnoreCase("y")) {
                break;
            }

        }

        return propertiesUserUpdatedList;
    }

    private void startSettingPropertySession(BasePropertyDto property) {
        String propertyName = new String(property.getName());
        String propertyType = new String(property.getPropertyType());
        String propertyRangeString = (property.getFrom() != null && property.getTo() != null)
                ?new String("from: " + property.getFrom() + "to: " + property.getTo()):null;
        Scanner scanner = new Scanner(System.in);

        setPropertyIntroduction();
        setPropertyDisplay(propertyName, propertyType, propertyRangeString);
        String value = scanner.nextLine();
        SimulatorResponse<SetPropertySimulatorResponseDto> resResponse = this.simulatorManager.setEnvironmentVariableValue(
                propertyName, propertyType, value);

        while (!resResponse.isSuccess()){
            System.out.println("Invalid value! " + resResponse.getData());
            setPropertyDisplay(propertyName, propertyType, propertyRangeString);
            value = scanner.nextLine();
            resResponse = this.simulatorManager.setEnvironmentVariableValue(propertyName, property.getPropertyType(), value);
        }

    }

    private void setPropertyIntroduction(){
        System.out.println("Initializing environment properties...\n");
    }

    private void setPropertyDisplay(String propertyName, String propertyType, String range){
        System.out.print("Property name: " + propertyName + "." + ((range != null)?"values must be " + range:""));
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
}
