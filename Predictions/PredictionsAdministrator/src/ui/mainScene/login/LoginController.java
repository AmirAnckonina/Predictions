package ui.mainScene.login;

import dto.loginOut.LoginResponseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import java.io.IOException;

import static utils.Constants.*;

public class LoginController {

    private MainController mainController;

    boolean loggedInFlag = false;

    @FXML
    private Button loginOutBtn;

    @FXML
    private Label userNameLbl;

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

// Create a VBox for the popup content
        VBox popupLayout = new VBox(10);
        popupLayout.setPadding(new Insets(10));

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

// Add the text field, login button, and cancel button to the popup layout
        popupLayout.getChildren().addAll(usernameTextField, loginButton, cancelButton);

        Scene popupScene = new Scene(popupLayout, 400, 200);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();

    }

    private void logoutRequest(){
        loginOutBtn.setText("Login");
        userNameLbl.setText("Welcome");
        mainController.logoutProcess();
        loggedInFlag = false;
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
                        userNameLbl.setText(userName);
                        //startStayAlive
                        loginOutBtn.setText("Logout");
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
