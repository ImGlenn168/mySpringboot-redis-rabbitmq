package com.java.myspringbootdemo02.App.service.user;

import com.java.myspringbootdemo02.Common.ar.AccountAr;
import com.java.myspringbootdemo02.Common.po.UserPo;
import com.java.myspringbootdemo02.Common.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<UserVo> findAll();

    int addUser(UserVo userVo);

    int updateUserById(UserVo userPo);

    int deleteUserById(UserVo user);

    List<UserVo>  findByPage(Map<String,Integer> map);

    int batchAdd(List<UserVo> list);
}
