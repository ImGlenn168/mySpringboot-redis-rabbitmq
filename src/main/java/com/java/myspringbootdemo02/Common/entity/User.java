package com.java.myspringbootdemo02.Common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.java.myspringbootdemo02.Common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
    @ExcelProperty("主键")
    private int id;
    @ExcelProperty("用户名")
    private String userName;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("部门")
    private String dept;
    @ExcelProperty("状态")
    private String status;
    @ExcelProperty("身份")
    private String state;
    @ExcelProperty("雇佣时间")
    private String hireTime;
}
