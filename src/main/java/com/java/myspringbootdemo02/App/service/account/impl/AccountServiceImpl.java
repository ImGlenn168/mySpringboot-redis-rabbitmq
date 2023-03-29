package com.java.myspringbootdemo02.App.service.account.impl;

import com.java.myspringbootdemo02.Common.ar.AccountAr;
import com.java.myspringbootdemo02.Common.po.AccountPO;
import com.java.myspringbootdemo02.Domain.persistence.IAccountDao;
import com.java.myspringbootdemo02.App.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<AccountAr> findAll() {
        List<AccountPO> accounts = accountDao.findAll();
        List<AccountAr> accountDtArs = new ArrayList<>();
        for (AccountPO account : accounts) {
            accountDtArs.add(new AccountAr(account.getId(), account.getName(), account.getMoney()));
        }
        return accountDtArs;
    }}
