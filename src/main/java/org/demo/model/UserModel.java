package org.demo.model;

/**
 * Created by hanhu on 16/6/27.
 */
//如格式010-12345678
public class UserModel {

    @Override
    public String toString() {
        return "UserModel{" +
                "areaCode='" + areaCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    private String areaCode;//区号
    private String phoneNumber;//电话号码

    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}