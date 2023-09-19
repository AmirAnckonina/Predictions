package UI.impl.javaFX.top.queue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueueManagementController {

    @FXML
    private ListView<Label> queueManagementLV;

    private Map<String, Label> labelsMap;

    public QueueManagementController() {
        this.labelsMap = new HashMap<>();
    }


    public void addItem(String line){
        try {
            labelsMap.put(line, new Label(line));
            queueManagementLV.getItems().add(labelsMap.get(line));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
    }

    public void removeItem(String line){
        try {
            queueManagementLV.getItems().remove(labelsMap.get(line));
            labelsMap.remove(line);
        }catch (Exception e){
            System.out.println("Label not found");
        }
    }
    public void cleanList(){
        queueManagementLV.getItems().clear();
        labelsMap.clear();
    }

}
