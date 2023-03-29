package com.java.myspringbootdemo02.Common.enums.user;

public enum UserStatusEnum {
    MANAGER(1,"管理员"),
    EMPLOYEE(2,"员工");

    private int code;
    private String status;

    UserStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public static UserStatusEnum getUserStatusByCode(int code){
        if (code==0){
            return UserStatusEnum.EMPLOYEE;
        }
        for (UserStatusEnum value : UserStatusEnum.values()) {
            if (code==value.getCode()){
                return value;
            }
        }
        return UserStatusEnum.EMPLOYEE;
    }
}
