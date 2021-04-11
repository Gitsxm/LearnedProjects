package org.example.demoEnum;

import javafx.scene.paint.Color;

/**
 * TODO 性别枚举
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-11 10:26 AM
 */
public enum Gender {
    男(1,"male"),
    女(2,"female");
    private int code;
    private String gender;

    Gender(int code, String gender) {
        this.code = code;
        this.gender = gender;
    }

    public int getCode() {
        return code;
    }

    public static Gender getEnumByCode(int code){
        for (Gender gender : Gender.values()){
            if (gender.code==code){
                return gender;
            }
        }
        return null;
    }

}
