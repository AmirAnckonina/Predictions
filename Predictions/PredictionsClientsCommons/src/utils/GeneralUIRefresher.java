package utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.TimerTask;
import java.util.function.Consumer;

public class GeneralUIRefresher<T> extends TimerTask {

    private final Consumer<T> UIConsumer;
    private final String endpoint;

    public GeneralUIRefresher(Consumer<T> UIConsumer, String endpoint) {
        this.UIConsumer = UIConsumer;
        this.endpoint = endpoint;
    }

    @Override
    public void run() {
        HttpClientUtil.runAsync(endpoint, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
               // T consumerObj = GSON_INSTANCE.fromJson(json, T.class);
               // UIConsumer.accept(consumerObj);
            }
        });
    }

}
