package UI.impl;
import UI.dynamicbody.DynamicInfoController;
import UI.staticheader.StaticHeaderController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;


public class AppController {
    @FXML private ScrollPane headerComponent;
    @FXML private StaticHeaderController headerComponentController;
    @FXML private ScrollPane bodyComponent;
    @FXML private DynamicInfoController bodyComponentController;

    @FXML
    public void initialize() {
        if (headerComponentController != null && bodyComponentController != null) {
            headerComponentController.setMainController(this);
            bodyComponentController.setMainController(this);
        }
    }

    public void setHeaderComponentController(StaticHeaderController headerComponentController) {
        this.headerComponentController = headerComponentController;
        headerComponentController.setMainController(this);
    }

    public void setBodyComponentController(DynamicInfoController bodyComponentController) {
        this.bodyComponentController = bodyComponentController;
        bodyComponentController.setMainController(this);
    }
}
