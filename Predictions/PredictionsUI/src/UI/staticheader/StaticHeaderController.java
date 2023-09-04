package UI.staticheader;

import UI.impl.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class StaticHeaderController {

    @FXML
    private BorderPane static_header_bp_id;

    @FXML
    private Label static_header_bp_details_lbl_id;

    @FXML
    private Label static_header_bp_results_lbl_id;

    @FXML
    private Button static_header_bp_load_file_btn_id;

    @FXML
    private Label static_header_bp_queue_mangement_lbl_id;

    @FXML
    private Label static_header_bp_title_lbl_id;

    @FXML
    private Label static_header_bp_load_file_lbl_id;

    private AppController mainController;

    @FXML
    void loadFileButtonActionListener(ActionEvent event) {

    }

    public void setMainController(AppController mainController) {
        this.mainController = mainController;
    }

}
