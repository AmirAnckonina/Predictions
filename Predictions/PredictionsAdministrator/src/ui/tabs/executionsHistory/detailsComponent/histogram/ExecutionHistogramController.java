package ui.tabs.executionsHistory.detailsComponent.histogram;

import dto.simulationInfo.PropertiesAvgConsistencyDto;
import dto.simulationInfo.PropertiesConsistencyDto;
import javafx.scene.input.MouseEvent;
import ui.tabs.executionsHistory.ExecutionsHistoryController;

import java.util.List;

public interface ExecutionHistogramController {
    void setPropertiesList(List<String> properties);
    void propertyNameClicked(MouseEvent event);
    void entityNameClickedLeftList(MouseEvent event);
    void setMainController(ExecutionsHistoryController mainController);
    void setLeftEntitiesList(List<String> entities);
    void setRightEntitiesList(List<String> entities);
    void clearRightEntityList();
    void clearPropertyList();
    void clearLeftEntityList();
    void setPropertiesConsistencyDto(PropertiesConsistencyDto propertiesConsistencyDto);
    void setPropertiesAvgConsistencyDto(PropertiesAvgConsistencyDto propertiesAvgConsistencyDto);
}
