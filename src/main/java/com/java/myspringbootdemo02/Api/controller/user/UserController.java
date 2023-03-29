package com.java.myspringbootdemo02.Api.controller.user;

import com.java.myspringbootdemo02.Api.result.Result;
import com.java.myspringbootdemo02.Common.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

public interface UserController {

    // 查询所有用户
    @GetMapping ("user/find")
    Result findAllUser();

    // 添加单个用户
    @PostMapping("user/addUser")
    Result addUser(@RequestBody UserVo userVo);

    // 修改单个用户
    @PostMapping("user/update")
    Result updateUserById(@RequestBody UserVo userPo);

    // 删除用户
    @PostMapping("user/delete")
    Result deleteUserById(@RequestBody UserVo user);

    // 分页查询用户
    @GetMapping("user/findByPage")
    Result findByPage(@PathParam("currentPage") int currentPage, @PathParam("pageSize") int pageSize);

    // 批量添加用户
    @PostMapping("user/batchAdd")
    Result batchAdd(List<UserVo> list);

    // 导出用户
    @GetMapping("/user/exportExcel")
    void ExportExcel();

    //* 导入用户
    @PostMapping("user/importExcel")
    Result ImportExcel(@RequestParam("file") MultipartFile file);
}
