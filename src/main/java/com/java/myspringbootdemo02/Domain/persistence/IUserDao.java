package com.java.myspringbootdemo02.Domain.persistence;

import com.java.myspringbootdemo02.Common.po.UserPo;
import com.java.myspringbootdemo02.Common.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface IUserDao {
     List<UserPo> findAll();

     int addUser(UserVo userVo);

     int updateUser(UserPo userPo);

     int deleteUserById(UserPo user);

     int batchAdd(List<UserVo> list);

     List<UserPo> findByPage(Map<String,Integer> map);
}
