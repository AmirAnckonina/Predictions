package utils;

import dto.ThreadPullDetailsDto;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.TimerTask;
import java.util.function.Consumer;

import static utils.Constants.GET_SIMULATION_WORLD_NAMES_LIST_ENDPOINT;
import static utils.Constants.GSON_INSTANCE;

public class ThreadPullDetailsRefresher  extends TimerTask {

    private final Consumer<ThreadPullDetailsDto> simulationThreadPullDetailsDtoConsumer;

    public ThreadPullDetailsRefresher(Consumer<ThreadPullDetailsDto> simulationThreadPullDetailsDtoConsumer) {
        this.simulationThreadPullDetailsDtoConsumer = simulationThreadPullDetailsDtoConsumer;
    }

    @Override
    public void run() {
        HttpClientUtil.runAsync(GET_SIMULATION_WORLD_NAMES_LIST_ENDPOINT, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace(System.out);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                try {
                    ThreadPullDetailsDto threadPullDetailsDto = GSON_INSTANCE.fromJson(json, ThreadPullDetailsDto.class);
                    simulationThreadPullDetailsDtoConsumer.accept(threadPullDetailsDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
