package com.example.mydailys.login;

import java.util.HashMap;
import java.util.Map;

public class User {

    HashMap<String, String> userMapper = new HashMap<String, String>();

    public void addUser(String username, String password) {
        userMapper.put(username, password);
    }

    public boolean checkUsername(String username) {
        return userMapper.containsKey(username);
    }

    public boolean verifyUser(String username, String password) {
        if (userMapper.containsKey(username)) {
            if (password.equals(userMapper.get(username))) {
                return true;
            }
        }
        return false;
    }

    public void loadUser(Map<String, ?> preferencesMap) {
        for(Map.Entry<String, ?> entries: preferencesMap.entrySet()) {
            if (!entries.getKey().equals("RememberMeCheckbox")) {
                userMapper.put(entries.getKey(), entries.getValue().toString());
            }
        }
    }
}
