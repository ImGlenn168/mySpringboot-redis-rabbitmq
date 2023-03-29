package com.java.myspringbootdemo02.Common.vo;

import com.java.myspringbootdemo02.Common.base.BaseEntity;
import com.java.myspringbootdemo02.Common.enums.user.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.NStringTypeHandler;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseEntity {
    private int id;
    private String userName;
    private String password;
    private String phone;
    private String dept;

    private int statusCode;
    private String status;
    private String hireTime;
    private int stateCode;
    private String state;

    public UserVo(String userName, String password, String phone,
                  String dept, int statusCode, String hireTime, int stateCode) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.dept = dept;
        this.statusCode = statusCode;
        this.status = status;
        this.hireTime = hireTime;
        this.stateCode = stateCode;
        this.state = state;
    }
}
