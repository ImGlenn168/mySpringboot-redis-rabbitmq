package com.java.myspringbootdemo02.Persistence.mapper.impl;

import com.java.myspringbootdemo02.Common.po.AccountPO;
import com.java.myspringbootdemo02.Domain.persistence.IAccountDao;
import com.java.myspringbootdemo02.Persistence.mapper.IAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountMapperImpl implements IAccountDao {

    @Autowired
    private IAccountMapper accountMapper;

    @Override
    public List<AccountPO> findAll() {
        return accountMapper.findAll();
    }
}
