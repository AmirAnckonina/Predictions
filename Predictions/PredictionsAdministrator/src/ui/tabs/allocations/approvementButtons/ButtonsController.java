package ui.tabs.allocations.approvementButtons;


import dto.SimulationRequestDetailsDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.tabs.allocations.AllocationsController;

public class ButtonsController {

    AllocationsController allocationsController;
    SimulationRequestDetailsDto simulationRequestDetailsDto;

    public ButtonsController(AllocationsController allocationsController, SimulationRequestDetailsDto simulationRequestDetailsDto) {
        this.allocationsController = allocationsController;
        this.simulationRequestDetailsDto = simulationRequestDetailsDto;
    }

    @FXML
    void noBtnClicked(ActionEvent event) {

    }

    @FXML
    void yesBtnClicked(ActionEvent event) {

    }

}
