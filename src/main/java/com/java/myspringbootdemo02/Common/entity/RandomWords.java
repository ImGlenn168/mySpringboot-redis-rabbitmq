package com.java.myspringbootdemo02.Common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomWords {
    private int code;
    private String content;
}
