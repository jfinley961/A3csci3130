package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Justin on 7/7/2017.
 */

public class BusinessData implements Serializable {
    public String uid;
    public String number;
    public String name;
    public String business;
    public String address;
    public String province;

    public BusinessData(){}

    public BusinessData(String uid, String number, String name, String business, String address, String province) {
        this.uid = uid;
        this.number = number;
        this.name = name;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("number", number);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
