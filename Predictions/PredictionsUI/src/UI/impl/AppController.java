package UI.impl;
import UI.dynamicbody.DynamicInfoController;
import UI.staticheader.StaticHeaderController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;


public class AppController {

    @FXML private StaticHeaderController headerController;
    @FXML private DynamicInfoController bodyController;
    @FXML private BorderPane main_layout_bp_id;

    public void setHeaderComponentController(StaticHeaderController headerComponentController) {
        this.headerController = headerComponentController;
        headerComponentController.setMainController(this);
    }

    public void setBodyComponentController(DynamicInfoController bodyComponentController) {
        this.bodyController = bodyComponentController;
        bodyComponentController.setMainController(this);
    }
}
