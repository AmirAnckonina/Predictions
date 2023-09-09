package UI.impl;
import UI.dynamicbody.DynamicInfoController;
import UI.dynamicbody.subbodyobjects.DetailsModule;
import UI.dynamicbody.subbodyobjects.ResultsModule;
import UI.staticheader.StaticHeaderController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import simulator.mainManager.api.SimulatorManager;
import simulator.mainManager.impl.SimulatorManagerImpl;
import simulator.result.manager.api.ResultManager;


public class AppController {
    @FXML private BorderPane headerComponent;
    @FXML private StaticHeaderController headerComponentController;
    @FXML private HBox bodyComponent;
    @FXML private DynamicInfoController bodyComponentController;

    private DetailsModule detailsModule;
    private ResultsModule resultsModule;


    @FXML
    public void initialize() {
        if (headerComponentController != null && bodyComponentController != null) {
            headerComponentController.setMainController(this);
            bodyComponentController.setMainController(this);
            this.detailsModule.setController(bodyComponentController);
            this.resultsModule.setController(bodyComponentController);
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
        this.bodyComponentController.updateListViewLinesOutput();
    }

    public boolean insertNewLineToLeftListView(String line){
        return bodyComponentController.insertNewLineToLeftListView(line);
    }

}
