package UI.impl;

import UI.api.UserInterface;
import dto.BuildSimulatorDto;
import dto.EnvironmentPropertiesDto;
import dto.SetPropertySimulatorResponseDto;
import dto.builder.params.BasePropertyDto;
import response.SimulatorResponse;
import simulator.manager.api.SimulatorManager;
import simulator.manager.impl.SimulatorManagerImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {

    private SimulatorManager simulator;

    @Override
    public void runSimulatorUI() {
        this.simulator = new SimulatorManagerImpl();
        BuildSimulatorDto buildSimulatorResult = this.simulator.buildSimulationWorld("resources/ex1-cigarets.xml");
    }

    public void activeEnvironmentSession() {
        EnvironmentPropertiesDto propertiesDto = this.simulator.getEnvironmentProperties();
        List<BasePropertyDto> properties = propertiesDto.getPropertiesList();
        printPropertiesList(properties);
        List<Integer> propertiesUserUpdatedList = handleUserChoice(properties);
        setRandomValuesForUninitializedProperties(propertiesUserUpdatedList, propertiesDto);

        this.simulator.activateEnvironment();
    }

    private void setRandomValuesForUninitializedProperties(List<Integer> propertiesUserUpdatedList, EnvironmentPropertiesDto propertiesDto) {
        //
    }

    private void printPropertiesList(List<BasePropertyDto> propertyDtoList){
        for (int i = 0; i < propertyDtoList.size(); i++) {
            System.out.println((i + 1) + ". \"" + propertyDtoList.get(i).getName() + "\"");
        }
    }

    private List<Integer> handleUserChoice(List<BasePropertyDto> propertyDtoList){
        System.out.println("Select a property to set a value.");
        System.out.print("Enter the index of the book: ");
        List<Integer> propertiesUserUpdatedList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
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

            System.out.print("Do you want to continue? (y/n): ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("y")) {
                break;
            }
        }

        return propertiesUserUpdatedList;
    }

    private void startSettingPropertySession(BasePropertyDto property) {
        String propertyName = new String(property.getName());
        String propertyType = new String(property.getPropertyType());
        Scanner scanner = new Scanner(System.in);

        setPropertyIntroduction();
        setPropertyDisplay(propertyName, propertyType);
        String value = scanner.nextLine();
        SimulatorResponse<SetPropertySimulatorResponseDto> resResponse = this.simulator.setEnvironmentVariableValue(
                propertyName, propertyType, value);

        while (!resResponse.isSuccess()){
            System.out.println("Illegal value! " + resResponse.getData());
            setPropertyDisplay(propertyName, propertyType);
            value = scanner.nextLine();
            resResponse = this.simulator.setEnvironmentVariableValue(propertyName, property.getPropertyType(), value);
        }

    }

    private void setPropertyIntroduction(){
        System.out.println("Initializing environment properties...\n");
    }

    private void setPropertyDisplay(String propertyName, String propertyType){
        System.out.print("Property name: " + propertyName + ".");
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
