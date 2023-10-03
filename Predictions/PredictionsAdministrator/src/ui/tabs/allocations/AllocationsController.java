package ui.tabs.allocations;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AllocationsController {

    @FXML
    private ListView<Label> clientsLV;

    @FXML
    private ListView<Label> simulationsLV;

    @FXML
    private ListView<Label> allocationsLV;

    @FXML
    private TextField numOfAllocationTA;

    @FXML
    private Button allocateBtn;

}
