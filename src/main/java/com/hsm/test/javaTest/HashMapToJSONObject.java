package com.hsm.test.javaTest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HashMapToJSONObject {
    public static void main(String[] args) throws JSONException {
        // 创建一个HashMap并添加一些数据
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "Alice");
        hashMap.put("age", 30);
        hashMap.put("city", "New York");
        HashMap<String, Object> people = new HashMap<>();
        people.put("peoplename", "zhangsan");
        people.put("peopleage", 18);
        hashMap.put("people", people);

        // 将HashMap转换为JSONObject
        JSONObject jsonObject = new JSONObject(hashMap);

        // 打印结果
        System.out.println(jsonObject.toString());

    }
}
