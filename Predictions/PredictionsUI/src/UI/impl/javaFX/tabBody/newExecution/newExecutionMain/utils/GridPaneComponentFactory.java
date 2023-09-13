package UI.impl.javaFX.tabBody.newExecution.newExecutionMain.utils;

import UI.impl.javaFX.utils.exception.PredictionsUIComponentException;
import enums.PropertyType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

import static UI.impl.javaFX.common.CommonResourcesPaths.*;

public final class GridPaneComponentFactory {
    public Node createGridPaneComponent(String keyName, PropertyType valueType) {

        FXMLLoader loader = new FXMLLoader();
        URL fxmlUrl;

        switch (valueType) {
            case STRING:
                 fxmlUrl = getClass().getResource(ENV_STRING_VAR_FXML_RESOURCE);
                 break;
            case FLOAT:
            case DECIMAL:
                fxmlUrl = getClass().getResource(ENV_FLOAT_VAR_FXML_RESOURCE);
                break;
            case BOOLEAN:
                fxmlUrl = getClass().getResource(ENV_BOOLEAN_VAR_FXML_RESOURCE);
                break;
            default:
                throw new PredictionsUIComponentException("Can't create GridPane for key-value");
        }

        loader.setLocation(fxmlUrl);



            singleComponent = loader.load();

            throw new PredictionsUIComponentException("failed to load component under GridPaneFactory.");

    }
}
