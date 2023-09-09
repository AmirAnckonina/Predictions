package UI.impl.javaFX.top;

import UI.impl.javaFX.mainScene.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class StaticHeaderController {

    private AppController mainController;


    @FXML
    private Label static_header_bp_details_lbl_id;

    @FXML
    private Label static_header_bp_new_execution_lbl_id;

    @FXML
    private Label static_header_bp_results_lbl_id;

    @FXML
    private Button static_header_bp_load_file_btn_id;

    @FXML
    private Label static_header_bp_title_lbl_id;

    @FXML
    private Label static_header_bp_load_file_lbl_id;

    @FXML
    void loadFileButtonActionListener(ActionEvent event) {
        this.mainController.loadFileButtonClicked();
    }

    public void setMainController(AppController mainController) {
        this.mainController = mainController;
    }

}
