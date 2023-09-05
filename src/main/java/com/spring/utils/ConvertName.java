package com.spring.utils;

public class ConvertName {

    public static String replaceAllSpace(String name) {
        String result = "";
        name = name.trim();
        name = name.replaceAll("\\s+", " ");
        return name;
    }
    public static String convertNameToCode(String name) {
        String result = "";
        String nameArr[] = name.split(" ");
        for (int i = 0; i < nameArr.length; i++) {
            nameArr[i] = nameArr[i].toLowerCase();
        }
        if(nameArr.length == 1) {
            return nameArr[0];
        }
        result += nameArr[nameArr.length - 1];
        for (int i = 0; i < nameArr.length - 1; i++) {
            if(nameArr[i] != null) {
                result += nameArr[i].charAt(0);
            }
        }
        return result;
    }


}
