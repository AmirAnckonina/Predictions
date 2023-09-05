package UI;

import UI.api.UserInterface;
import UI.impl.ConsoleUI;
import UI.impl.GuiUI;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUI();
        Application UI = new GuiUI();
        UI.launch();
        userInterface.runSimulatorUI();
    }

}
