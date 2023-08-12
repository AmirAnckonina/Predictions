package UI.impl;

import UI.api.UserInterface;
import dto.BuildSimulatorDto;
import dto.EnvironmentPropertiesDto;
import dto.builder.params.BasePropertyDto;
import simulator.manager.api.SimulatorManager;
import simulator.manager.impl.SimulatorManagerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {
    @Override
    public void runSimulatorUI() {
        SimulatorManager simulator = new SimulatorManagerImpl();
        BuildSimulatorDto buildSimulatorResult = simulator.buildSimulationWorld("resources/ex1-cigarets.xml");
    }

    public void activeEnvironmentSession() {
        SimulatorManager simulator = new SimulatorManagerImpl();
        EnvironmentPropertiesDto propertiesDto = simulator.getEnvironmentProperties();
        List<BasePropertyDto> properties = propertiesDto.getPropertiesList();
        for (BasePropertyDto property : properties
        ) {
            setPropertySession();
        }
    }

    private void setPropertySession(BasePropertyDto property, SimulatorManager simulatorManager) {
        String propertyName = new String(property.getName());
        String propertyType = new String(property.getPropertyType());
        Scanner scanner = new Scanner(System.in);

        setPropertyIntroduction();
        setPropertyDisplay(propertyName, propertyType);
        String value = scanner.nextLine();
        BasePropertyDto responseDto = new BasePropertyDto(propertyName, propertyType, property.getFrom(),
                property.getTo(), value);

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
