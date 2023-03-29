package com.java.myspringbootdemo02.Common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPO {
    private int id;

    private String name;

    private double money;
}
