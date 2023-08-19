package response;

public class SimulatorResponse<T> {
    private boolean success;
    private String message;
    private T data;
    public SimulatorResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public SimulatorResponse(boolean success, String message) {
        this(success, message, null);
    }

    public SimulatorResponse(boolean success, T data) {

        this(success, "", data);
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

}
