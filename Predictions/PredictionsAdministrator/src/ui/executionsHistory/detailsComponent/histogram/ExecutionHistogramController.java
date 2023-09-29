package ui.executionsHistory.detailsComponent.histogram;

import dto.PropertiesAvgConsistencyDto;
import dto.PropertiesConsistencyDto;
import javafx.scene.input.MouseEvent;
import ui.executionsHistory.ExecutionsHistoryController;

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
