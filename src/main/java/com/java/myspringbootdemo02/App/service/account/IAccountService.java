package com.java.myspringbootdemo02.App.service.account;

import com.java.myspringbootdemo02.Common.ar.AccountAr;

import java.util.List;

public interface IAccountService {

    List<AccountAr> findAll();
}
