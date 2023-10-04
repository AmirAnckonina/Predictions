package ui.tabs.allocations.approvementButtons;


import dto.orderRequest.SimulationOrderRequestDetailsDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.tabs.allocations.AllocationsController;

public class ButtonsController {

    AllocationsController allocationsController;
    SimulationOrderRequestDetailsDto simulationRequestDetailsDto;

    public ButtonsController(AllocationsController allocationsController, SimulationOrderRequestDetailsDto simulationRequestDetailsDto) {
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
