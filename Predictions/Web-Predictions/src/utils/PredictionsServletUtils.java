package utils;

import com.google.gson.Gson;
import common.Constants;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import simulator.builder.manager.api.WorldBuilderManager;
import simulator.establishment.manager.api.EstablishmentManager;
import simulator.execution.manager.api.ExecutionManager;
import simulator.information.manager.api.InformationManager;
import simulator.mainManager.api.SimulatorManager;
import simulator.manualSetup.manager.api.ManualSimulationSetupManager;
import simulator.store.api.StoreManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PredictionsServletUtils {

    public final static Gson GSON_INSTANCE = new Gson();
    public static SimulatorManager getSimulatorManager(ServletContext servletContext) {
        return (SimulatorManager) servletContext.getAttribute(Constants.SIMULATOR_MANAGER_ATTRIBUTE_NAME);
    }

    public static WorldBuilderManager getWorldBuilderManager(ServletContext servletContext) {
        return (WorldBuilderManager) servletContext.getAttribute(Constants.WORLD_BUILDER_MANAGER_ATTRIBUTE_NAME);
    }

    public static InformationManager getInfoManager(ServletContext servletContext) {
        return (InformationManager) servletContext.getAttribute(Constants.INFO_MANAGER_ATTRIBUTE_NAME);
    }

    public static EstablishmentManager getEstablishmentManager(ServletContext servletContext) {
        return (EstablishmentManager) servletContext.getAttribute(Constants.ESTABLISHMENT_MANAGER_ATTRIBUTE_NAME);
    }

    public static ManualSimulationSetupManager getManualSetupManager(ServletContext servletContext) {
        return (ManualSimulationSetupManager) servletContext.getAttribute(Constants.MANUAL_SETUP_MANAGER_ATTRIBUTE_NAME);
    }

    public static ExecutionManager getExecutionManager(ServletContext servletContext) {
        return (ExecutionManager) servletContext.getAttribute(Constants.EXECUTION_MANAGER_ATTRIBUTE_NAME);
    }

    public static StoreManager getStoreManager(ServletContext servletContext) {
        return (StoreManager) servletContext.getAttribute(Constants.STORE_MANAGER_ATTRIBUTE_NAME);
    }


    public static String getStringBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[256];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public static InputStream getInputStreamBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;

        try {
            inputStream = request.getInputStream();

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        return inputStream;
    }


}
