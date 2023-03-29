package com.java.myspringbootdemo02.Common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
    private int id;
    private String userName;
    private String password;
    private String phone;

    private String dept;
    private int status;
    private String hireTime;

    private Date createTime;
    private Date updateTime;

    private int state;
}
