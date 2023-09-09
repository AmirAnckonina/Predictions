package UI.impl.javaFX.app;
import UI.dynamicbody.DynamicInfoController;
import UI.staticheader.StaticHeaderController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class AppController {
    @FXML private BorderPane headerComponent;
    @FXML private StaticHeaderController headerComponentController;
    @FXML private HBox bodyComponent;
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

    public void loadFileButtonClicked() {
        System.out.println("loadFileButtonActionListener");
        System.out.println("loadFileButtonActionListener");
    }
}
