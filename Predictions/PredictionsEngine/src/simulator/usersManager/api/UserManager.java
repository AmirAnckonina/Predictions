package simulator.usersManager.api;

import dto.loginOut.LoginResponseDto;

import java.util.List;

public interface UserManager {
    LoginResponseDto loginUserRequest(String userName);
    LoginResponseDto loginAdminRequest(String userName);
    List<String> getAllUsers();
    void stayAliveUpdate(String userName);
    Boolean isUserNameConnected(String userName);
}
