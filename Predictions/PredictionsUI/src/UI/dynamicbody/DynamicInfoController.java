package UI.dynamicbody;

import UI.dynamicbody.subbodyobjects.simulationTitle;
import UI.impl.AppController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class DynamicInfoController {

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

    private int observableLinesIndex = -1;
    private AppController mainController;

    public void setMainController(AppController mainController) {
        this.mainController = mainController;
    }

    public void updateListViewLinesOutput(){
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
