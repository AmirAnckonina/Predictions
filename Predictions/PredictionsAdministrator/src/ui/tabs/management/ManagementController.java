package ui.tabs.management;

import dto.ThreadPullDetailsDto;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ui.mainScene.MainController;
import ui.tabs.management.details.DetailsController;
import utils.HttpClientUtil;
import utils.SimulationWorldListRefresher;
import utils.ThreadPullDetailsRefresher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.*;
import static utils.Constants.SIMULATION_WORLD_LIST_REFRESH_RATE;

public class ManagementController {

    @FXML
    private Label currLoadedFilePathLbl;

    @FXML
    private Button setThreadsCountButton;

    @FXML
    private GridPane availableSimulationDetailsGrid;

    @FXML
    private GridPane threadPoolManagementGrid;

    @FXML
    private Label waitingValueLabel;

    @FXML
    private Label runningValueLabel;

    @FXML
    private Label runningLabel;

    @FXML
    private Label finishedValueLabel;

    @FXML
    private Button setNumOfThreadBtn;

    @FXML
    private Label setThreadMassageLbl;

    @FXML
    private DetailsController detailsController;

    private MainController mainController;
    private Stage primaryStage;
    private TimerTask threadPullDetailsRefresher;
    private Timer threadPullDetailsTimer;

    @FXML
    public void initialize() {
    }


    @FXML
    void loadFileButtonClicked(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select simulation");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Simulation", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile == null) {
                return;
            }
            String absolutePath = selectedFile.getAbsolutePath();
//            mainController.resetGUI();
            this.currLoadedFilePathLbl.setText(absolutePath);
//            this.mainController.onLoadSimulationButtonClicked(absolutePath);
            BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
            List<String> lines = new ArrayList<>();
            reader.lines().forEach(line -> lines.add(line + "\n"));
            loadSimulationToServer(parseStreamToStrings(lines));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
    }



    @FXML
    void setNumOfThreadBtnClicked(ActionEvent event) {

    }

    @FXML
    void setThreadsCountButtonClicked(ActionEvent event) {

    }

    public void setActive(){
        detailsController.setActive();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void loadSimulationToServer(byte[] simulationInBytes){
        //byte[] b = xmlString.getBytes("UTF-8");
        RequestBody requestBody = RequestBody.create(simulationInBytes);
        String finalUrl = HttpUrl
                .parse(POST_NEW_SIMULATION_LOAD_ENDPOINT)
                .newBuilder()
                .build()
                .toString();

        HttpClientUtil.runAsync(finalUrl, requestBody, new Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    SimulationWorldDetailsDto simulationWorldDetailsDto = GSON_INSTANCE.fromJson(responseBody, SimulationWorldDetailsDto.class);
//                    Platform.runLater(() ->
//                            updateDetailsComponentUI(simulationWorldDetailsDto)
//                    );
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getSimulationWorldDetailsProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }
        });
//        Platform.runLater(() -> {
//            chatLineTextArea.clear();
//        });
    }

    private void updateThreadDetailsComponentUI(ThreadPullDetailsDto threadPullDetailsDto) {
        Platform.runLater(() -> {
            waitingValueLabel.setText(threadPullDetailsDto.getWaitingSimulations().toString());
            runningLabel.setText(threadPullDetailsDto.getRunningSimulations().toString());
            finishedValueLabel.setText(threadPullDetailsDto.getTerminatedSimulations().toString());
        });
    }

    private void startSimulationWorldListRefresher() {
        this.threadPullDetailsRefresher = new ThreadPullDetailsRefresher(this::updateThreadDetailsComponentUI);
        this.threadPullDetailsTimer = new Timer();
        this.threadPullDetailsTimer.schedule(threadPullDetailsRefresher, SIMULATION_WORLD_LIST_REFRESH_RATE, SIMULATION_WORLD_LIST_REFRESH_RATE);
    }

    // Example method to create a sample input stream of strings
    private static InputStream createSampleInputStream(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringList) {
            stringBuilder.append(str);
        }
        return new java.io.ByteArrayInputStream(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }

    // Method to parse a stream of strings into a byte array
    private static byte[] parseStreamToStrings(List<String> stringArray) throws IOException {
        InputStream inputStream = createSampleInputStream(stringArray);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // You can adjust the buffer size as needed
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }
}