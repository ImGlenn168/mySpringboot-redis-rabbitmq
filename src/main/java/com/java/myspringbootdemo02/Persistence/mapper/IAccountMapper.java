package com.java.myspringbootdemo02.Persistence.mapper;

import com.java.myspringbootdemo02.Common.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IAccountMapper {

    List<AccountPO> findAll();
}
