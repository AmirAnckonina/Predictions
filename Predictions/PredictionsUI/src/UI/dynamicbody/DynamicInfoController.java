package UI.dynamicbody;

import UI.impl.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class DynamicInfoController {

    @FXML
    private ListView<?> main_info_hbox_left_list_sp_id;

    private AppController mainController;

    public void setMainController(AppController mainController) {
        this.mainController = mainController;
    }

}
