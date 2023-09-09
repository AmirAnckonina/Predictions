package UI.impl.javaFX.mainScene;
import UI.impl.javaFX.tabBody.details.DynamicInfoController;
import UI.impl.javaFX.tabBody.details.DetailsModule;
import UI.impl.javaFX.top.StaticHeaderController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class AppController {
    @FXML private BorderPane headerComponent;
    @FXML private StaticHeaderController headerComponentController;
    @FXML private HBox bodyComponent;
    @FXML private DynamicInfoController bodyComponentController;

    private DetailsModule detailsModule;


    @FXML
    public void initialize() {
        if (headerComponentController != null && bodyComponentController != null) {
            headerComponentController.setMainController(this);
            bodyComponentController.setMainController(this);
            this.detailsModule.setController(bodyComponentController);
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
