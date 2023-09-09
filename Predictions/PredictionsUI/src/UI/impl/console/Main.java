package UI.impl.console;

import UI.api.UserInterface;
import UI.impl.console.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUI();
        //Application UI = new GuiUI();
        //UI.launch();
        userInterface.runSimulatorUI();
    }

}
