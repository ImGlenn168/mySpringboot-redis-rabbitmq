package com.java.myspringbootdemo02.Api.controller.user.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.StringUtils;
import com.java.myspringbootdemo02.Api.controller.user.UserController;
import com.java.myspringbootdemo02.Api.result.Result;
import com.java.myspringbootdemo02.App.service.user.impl.UserServiceImpl;
import com.java.myspringbootdemo02.Common.entity.User;
import com.java.myspringbootdemo02.Common.enums.user.UserStateEnum;
import com.java.myspringbootdemo02.Common.enums.user.UserStatusEnum;
import com.java.myspringbootdemo02.Common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserControllerImpl implements UserController {

    @Autowired
    @Qualifier(value = "userService")
    private UserServiceImpl userService;

    public Result findAllUser() {
        List<UserVo> userVos = userService.findAll();
        return Result.success(userVos);
    }

    @Override
    public Result addUser(@RequestBody UserVo userVo) {
        int i = userService.addUser(userVo);
        return getResult(i);
    }

    private Result getResult(int i) {
        if (i > 0) {
            return Result.success("true");
        }
        return Result.fail("false");
    }

    @Override
    public Result updateUserById(UserVo userPo) {
        int i = userService.updateUserById(userPo);
        return getResult(i);
    }

    @Override
    public Result deleteUserById(UserVo user) {
        int i = userService.deleteUserById(user);
        return getResult(i);
    }

    @Override
    public Result findByPage(int currentPage, int pageSize) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        return Result.success(userService.findByPage(map));
    }

    @Override
    public Result batchAdd(List<UserVo> list) {
        int i = userService.batchAdd(list);
        return getResult(i);
    }


    public void ExportExcel() {
        // 设置文件导出的路径
        String realPath = "E://wsfile/";
        File folder = new File(realPath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String fileName = realPath + "User" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为用户表 然后文件流会自动关闭
        EasyExcel.write(fileName, User.class).sheet("用户表").doWrite(getData());
    }

    public List<User> getData() {
        // 查询用户表,具体service层实现就不写了
        List<UserVo> all = userService.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (UserVo userVo : all) {
            users.add(getUser(userVo));
        }
        return users;
    }


    public Result ImportExcel(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return Result.fail();
        }
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        try {
            ArrayList<UserVo> userVos = new ArrayList<>();
            EasyExcel.read(multipartFile.getInputStream(), User.class, new PageReadListener<User>(dataList -> {
                for (User user : dataList) {
                    UserVo userVo = getUserVo(user);
                    userVos.add(userVo);
                }
                //将导入的数据用mybatisPlus一个个添加进数据库
                userService.batchAdd(userVos);
            })).sheet().doRead();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    private static UserVo getUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        setPassword(user, userVo);
        userVo.setPhone(user.getPhone());
        userVo.setDept(user.getDept());
        setState(user, userVo);
        setStatus(user, userVo);
        userVo.setHireTime(user.getHireTime());
        userVo.setCreateTime(user.getCreateTime());
        userVo.setUpdateTime(user.getUpdateTime());
        return userVo;
    }

    private static void setPassword(User user, UserVo userVo) {
        if (!StringUtils.isEmpty(user.getPassword())){
            userVo.setPassword(user.getPassword());
        }else {
            userVo.setPassword("112233");
        }
    }

    private static void setStatus(User user, UserVo userVo) {
        if (UserStateEnum.USING.getState().equals(user.getStatus()) && !StringUtils.isEmpty(user.getStatus())){
            userVo.setStatusCode(1);
        }else {
            userVo.setStatusCode(-1);
        }
    }

    private static void setState(User user, UserVo userVo) {
        if (UserStatusEnum.MANAGER.getStatus().equals(user.getState()) && !StringUtils.isEmpty(user.getState())){
            userVo.setStateCode(1);
        }else {
            userVo.setStateCode(2);
        }
    }

    private static User getUser(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setPhone(userVo.getPhone());
        user.setDept(userVo.getDept());
        user.setStatus(userVo.getStatus());
        user.setState(userVo.getState());
        user.setHireTime(userVo.getHireTime());
        user.setHireTime(userVo.getHireTime());
        user.setUpdateTime(userVo.getUpdateTime());
        user.setCreateTime(userVo.getCreateTime());
        return user;
    }
}
