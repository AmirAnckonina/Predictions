package utils;

import com.google.gson.reflect.TypeToken;
import dto.orderRequest.SimulationOrderRequestDetailsDto;
import javafx.beans.property.SimpleBooleanProperty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.function.Consumer;

import static utils.Constants.*;

public class StayAliveRefresher  extends TimerTask {
    private String userName;
    private Consumer<String> updateFunc;

    public StayAliveRefresher(String userName, Consumer<String> updateFunc) {
        this.userName = userName;
        this.updateFunc = updateFunc;
    }

    @Override
    public void run() {
        String finalUrl =
                HttpUrl
                        .parse(GET_STAY_ALIVE_UPDATE_ENDPOINT) //Anko - //GET_LOGIN_USER_ENDPOINT
                        .newBuilder()
                        .addQueryParameter(POST_NEW_SIMULATION_REQUEST_PARAM_KEY, GSON_INSTANCE.toJson(userName))
                        .build()
                        .toString();
        HttpClientUtil.runAsync(finalUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    updateFunc.accept(userName);
                } else {
                    System.out.println("error code = " + response.code() + ". Something went wrong with the request GET_SIMULATION_REQUESTS_LIST_ENDPOINT...:(");
                }
            }
        });

    }
}
