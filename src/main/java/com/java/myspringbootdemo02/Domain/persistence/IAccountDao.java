package com.java.myspringbootdemo02.Domain.persistence;

import com.java.myspringbootdemo02.Common.po.AccountPO;

import java.util.List;

public interface IAccountDao {
     List<AccountPO> findAll();
}
