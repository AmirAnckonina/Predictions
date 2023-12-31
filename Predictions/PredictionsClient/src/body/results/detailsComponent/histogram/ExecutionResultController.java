package body.results.detailsComponent.histogram;

import body.results.ResultsController;
import dto.simulationInfo.PropertiesAvgConsistencyDto;
import dto.simulationInfo.PropertiesConsistencyDto;
import javafx.scene.input.MouseEvent;

import java.util.List;

public interface ExecutionResultController {
    void setPropertiesList(List<String> properties);
    void propertyNameClicked(MouseEvent event);
    void entityNameClickedLeftList(MouseEvent event);
    void setMainController(ResultsController mainController);
    void setLeftEntitiesList(List<String> entities);
    void setRightEntitiesList(List<String> entities);
    void clearRightEntityList();
    void clearPropertyList();
    void clearLeftEntityList();
    void setPropertiesConsistencyDto(PropertiesConsistencyDto propertiesConsistencyDto);
    void setPropertiesAvgConsistencyDto(PropertiesAvgConsistencyDto propertiesAvgConsistencyDto);
}
