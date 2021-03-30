package com.company;

import java.util.HashMap;

public class IDandPasswords {
    //create a hash map logininfo
    HashMap<String,String> logininfo = new HashMap<String, String>(); //store key value username and password
    //create a constructor class for id and pswd
    IDandPasswords(){
        logininfo.put("Nathalie", "123");
        logininfo.put("Heyeeun", "456");
        logininfo.put("Liubov", "789");
    }

    //create a getter method for this hash map
    protected HashMap getLoginInfo() { //privacy status of protected, anybody can call this method
        return logininfo;
    }

}

