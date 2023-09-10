package UI.impl.javaFX.tabBody.details;

import UI.impl.javaFX.mainScene.PredictionsMainController;
import UI.impl.javaFX.tabBody.details.subbodyobjects.simulationTitle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class DetailsController {
    private int observableLinesIndex = -1;
    private PredictionsMainController mainController;

    private Stage primaryStage;

    @FXML
    private ListView<?> main_info_hbox_left_list_sp_id;

    @FXML
    private ListView<simulationTitle> main_info_hbox_left_list_lv_id;


    private ObservableList<simulationTitle> listViewLines = FXCollections.observableArrayList(
            new simulationTitle("test1"),
            new simulationTitle("test2"),
            new simulationTitle("test3"),
            new simulationTitle("test4")
    );

    public void setMainController(PredictionsMainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadFileButtonClicked(){
        this.main_info_hbox_left_list_lv_id.setItems(this.listViewLines);
    }

    public boolean insertNewLineToLeftListView(String simulationID){
        this.listViewLines.add(new simulationTitle(simulationID));
        return true;
    }

    @FXML
    void listViewLineClicked(MouseEvent event) {
        String selectedSimulationID = this.main_info_hbox_left_list_lv_id.
                getSelectionModel().getSelectedItem().toString();

        System.out.println(selectedSimulationID);
    }


}