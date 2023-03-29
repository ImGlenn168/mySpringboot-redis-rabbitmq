package com.java.myspringbootdemo02.Common.ar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountAr {
    private Integer id;

    private String name;

    private Double money;
}
