package UI;

import UI.api.UserInterface;
import UI.impl.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUI();
        userInterface.runSimulatorUI();

    }

}
