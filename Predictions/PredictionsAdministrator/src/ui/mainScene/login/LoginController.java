package ui.mainScene.login;

import dto.loginOut.LoginResponseDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ui.mainScene.MainController;
import utils.HttpClientUtil;
import utils.StayAliveRefresher;

import java.io.IOException;
import java.util.Timer;

import static utils.Constants.*;

public class LoginController {

    private MainController mainController;

    private boolean loggedInFlag = false;
    private SimpleStringProperty loginOutBtnText = new SimpleStringProperty();
    private SimpleStringProperty userNameLblText = new SimpleStringProperty();

    private StayAliveRefresher stayAliveRefresher;
    private Timer stayAliveTimer;

    @FXML
    private Button loginOutBtn;

    @FXML
    private Label userNameLbl;

    @FXML
    public void initialize() {
        userNameLbl.textProperty().bind(userNameLblText);
        loginOutBtn.textProperty().bind(loginOutBtnText);
        loginOutBtnText.set("Login");
        userNameLblText.set("Welcome");
    }

    @FXML
    void loginOutClicked(ActionEvent event) {
        if(!loggedInFlag){
            openPopup();
        }else {
            logoutRequest();
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void openPopup() {
        // Create a new stage for the popup window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Login");

        // Create an HBox for the button layout
        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10));

        // Create a text field for entering the username
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter username");

        // Create a login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String userName = usernameTextField.getText();
            sendLoginRequest(userName);
            // You can add your logic to handle the username here

            popupStage.close(); // Close the popup window
        });

        // Create a cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            popupStage.close(); // Close the popup window
        });

        // Set the same width for both buttons
        loginButton.setMinWidth(80);
        cancelButton.setMinWidth(80);

        // Add the buttons to the button layout
        buttonLayout.getChildren().addAll(loginButton, cancelButton);

        // Create a VBox for the text field and button layout
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(10));
        popupLayout.getChildren().addAll(usernameTextField, buttonLayout);

        Scene popupScene = new Scene(popupLayout, 400, 100);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

    private void logoutRequest(){
        loginOutBtnText.set("Login");
        userNameLblText.set("Welcome");
        mainController.logoutProcess();
        loggedInFlag = false;
        stayAliveRefresher.cancel();
    }

    private void updateLoginOutLbl(String userName){
        loginOutBtnText.set("Logout");
        userNameLblText.set(userName);
    }

    private void startStayAliveRefresher(String userName){
        stayAliveRefresher = new StayAliveRefresher(userName, this::updateLoginOutLbl);
        this.stayAliveTimer = new Timer();
        this.stayAliveTimer.schedule(stayAliveRefresher, SIMULATION_WORLD_LIST_REFRESH_RATE, SIMULATION_WORLD_LIST_REFRESH_RATE);
    }

    private void sendLoginRequest(String userName) {
        String finalUrl =
                HttpUrl
                        .parse(GET_LOGIN_ADMIN_ENDPOINT) //Anko - //GET_LOGIN_USER_ENDPOINT
                        .newBuilder()
                        .addQueryParameter(POST_NEW_SIMULATION_REQUEST_PARAM_KEY, GSON_INSTANCE.toJson(userName))
                        .build()
                        .toString();

        HttpClientUtil.runAsync(finalUrl, new Callback() {


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    LoginResponseDto loginResponseDto = GSON_INSTANCE.fromJson(responseBody, LoginResponseDto.class);
                    if(loginResponseDto.isAnswer()){
                        startStayAliveRefresher(userName);
                        mainController.loginProcess();
                        loggedInFlag = true;
                    }else {
                        userNameLbl.setText(loginResponseDto.getMassage());
                    }
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request getSimulationWorldDetailsProcedure()...:(");
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                userNameLbl.setText("Couldn't reach out to the server");
                e.printStackTrace(System.out);
            }
        });
    }
}
