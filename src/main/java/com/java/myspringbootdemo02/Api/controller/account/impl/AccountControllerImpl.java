package com.java.myspringbootdemo02.Api.controller.account.impl;

import com.java.myspringbootdemo02.Api.controller.account.AccountController;
import com.java.myspringbootdemo02.Api.result.Result;
import com.java.myspringbootdemo02.Common.ar.AccountAr;
import com.java.myspringbootdemo02.Common.dto.AccountDto;
import com.java.myspringbootdemo02.App.service.account.impl.AccountServiceImpl;
import com.java.myspringbootdemo02.External.restTemplate.randomwords.RandomWordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    @Qualifier(value = "accountService")
    private AccountServiceImpl accountService;

    @Autowired
    private RandomWordsDao randomWordsDao;

    public Result findAll(){
        List<AccountAr> arList = accountService.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        for (AccountAr account : arList) {
            accountDtos.add(new AccountDto(account.getId(), account.getName(), account.getMoney()));
        }
        return Result.success(accountDtos);
    }

    @Override
    public String test01() {
        return randomWordsDao.getWords();
    }
}
