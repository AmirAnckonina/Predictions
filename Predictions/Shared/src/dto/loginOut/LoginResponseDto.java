package dto.loginOut;

public class LoginResponseDto {
    String userName;
    boolean answer;
    String massage;

    public LoginResponseDto(String userName, boolean answer, String massage) {
        this.userName = userName;
        this.answer = answer;
        this.massage = massage;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAnswer() {
        return answer;
    }

    public String getMassage() {
        return massage;
    }
}
