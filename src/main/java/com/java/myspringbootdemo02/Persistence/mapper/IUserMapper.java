package com.java.myspringbootdemo02.Persistence.mapper;

import com.java.myspringbootdemo02.Common.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUserMapper {

    List<UserPo> findAll();
    List<UserPo> findByPage(Map<String,Integer> map);

    int addUser(UserPo userPo);

    int updateUserById(UserPo userPo);

    int deleteUserById(UserPo user);
    int batchAdd(List<UserPo> list);

}
