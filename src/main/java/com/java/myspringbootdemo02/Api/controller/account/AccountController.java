package com.java.myspringbootdemo02.Api.controller.account;

import com.java.myspringbootdemo02.Api.result.Result;
import org.springframework.web.bind.annotation.GetMapping;

public interface AccountController {

    @GetMapping ("account/find")
    Result findAll();

    @GetMapping("account/test01")
    String test01();

}
