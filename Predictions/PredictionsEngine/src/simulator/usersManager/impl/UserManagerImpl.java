package simulator.usersManager.impl;

import dto.loginOut.LoginResponseDto;
import simulator.usersManager.api.UserManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerImpl implements UserManager {
    Map<String, Long> userNameMap;
    Map<String, Long> adminNameMap;

    public static final Long UPDATE_REQUIRED_TIME = new Long(5000);


    public UserManagerImpl() {
        this.userNameMap = new HashMap<>();
        this.adminNameMap = new HashMap<>();
    }

    @Override
    public LoginResponseDto loginUserRequest(String userName) {
        LoginResponseDto responseDto;
        Long time = userNameMap.get(userName);
        if(time != null && UPDATE_REQUIRED_TIME < System.currentTimeMillis() - time){
            responseDto = new LoginResponseDto(userName, false, userName + " already logged-in");
        }else {
            userNameMap.put(userName, System.currentTimeMillis());
            responseDto = new LoginResponseDto(userName, true, "User name connected successfully");
        }

        return responseDto;
    }

    @Override
    public LoginResponseDto loginAdminRequest(String adminName) {
        LoginResponseDto responseDto;
        Long time = adminNameMap.get(adminName);
        if(time != null && adminNameMap.keySet().size() > 0 && UPDATE_REQUIRED_TIME < System.currentTimeMillis() - time){
            responseDto = new LoginResponseDto(adminName, false, "Admin already logged-in");
        }else {
            adminNameMap.put(adminName, System.currentTimeMillis());
            responseDto = new LoginResponseDto(adminName, true, adminName + " name connected successfully");
        }

        return responseDto;
    }

    @Override
    public List<String> getAllUsers() {
        List<String> userList = new ArrayList<>();
        userNameMap.keySet().forEach(name -> userList.add(name));
        return userList;
    }

    @Override
    public void stayAliveUpdate(String userName) {
        if(userNameMap.get(userName) != null){
            userNameMap.put(userName, System.currentTimeMillis());
        } else if (adminNameMap.get(userName) != null) {
            adminNameMap.put(userName, System.currentTimeMillis());
        }
    }

    @Override
    public Boolean isUserNameConnected(String userName) {
        Boolean returnVal = false;
        if(userNameMap.get(userName) != null || adminNameMap.get(userName) != null){
            returnVal = true;
        }

        return returnVal;
    }
}
