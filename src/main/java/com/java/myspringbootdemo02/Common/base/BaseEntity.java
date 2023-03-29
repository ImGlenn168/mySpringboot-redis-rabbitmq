package com.java.myspringbootdemo02.Common.base;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @ExcelProperty("创建时间")
    private String createTime;
    @ExcelProperty("修改时间")
    private String updateTime;
}
