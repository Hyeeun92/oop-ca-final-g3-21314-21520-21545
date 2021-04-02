package com.company;

import com.mysql.cj.log.Log;

import java.util.HashMap;

public class LoginInfo {

    String type;
    String id;
    String password;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

   @Override
    public String toString() {
        return String.format("%s %s %s", type, id, password);

    }

}


