package com.java.myspringbootdemo02.test;

import com.java.myspringbootdemo02.Common.po.UserPo;
import com.java.myspringbootdemo02.Common.vo.UserVo;
import com.java.myspringbootdemo02.Domain.persistence.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserUnitTest {

    private static final Logger log= LoggerFactory.getLogger(UserUnitTest.class);
    @Autowired
    private IUserDao userDao;

    @Test
    public void testAdd(){
        UserVo userVo = new UserVo();
        userVo.setUserName("user03");
        userVo.setPassword("112233");
        userVo.setPhone("8888888888");
        userVo.setDept("事业部");
        userVo.setStatusCode(2);
        userVo.setHireTime("2023-03-21");
        userVo.setStateCode(1);
        userDao.addUser(userVo);
        log.info("添加成功！！！");
    }

    @Test
    public void testBatchAdd(){
        ArrayList<UserVo> userVos = new ArrayList<>();
        UserVo userVo1 = new UserVo("user03","112233","8888888888","事业部",2,"2023-03-21",1);
        UserVo userVo2 = new UserVo("user03","112233","8888888888","事业部",2,"2023-03-21",1);
        UserVo userVo3 = new UserVo("user03","112233","8888888888","事业部",2,"2023-03-21",1);
        UserVo userVo4 = new UserVo("user03","112233","8888888888","事业部",2,"2023-03-21",1);
        userVos.add(userVo1);
        userVos.add(userVo2);
        userVos.add(userVo3);
        userVos.add(userVo4);
        userDao.batchAdd(userVos);
        log.info("添加成功！！！");
    }

    @Test
    public void testUpdate(){
        UserPo userPo = new UserPo();
        userPo.setId(7);
        userPo.setUserName("aaaaa");
        userPo.setPassword("112233");
        userPo.setPhone("8888888888");
        userPo.setDept("事业部");
        userPo.setStatus(2);
        userPo.setHireTime("2023-03-21");
        userPo.setState(1);
        userDao.updateUser(userPo);
        log.info("修改成功！！！");
    }

    @Test
    public void testDelete(){
        UserPo userPo = new UserPo();
        userPo.setId(9);
        userDao.deleteUserById(userPo);
        log.info("删除成功！！！");
    }

    @Test
    public void testPageResult(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize",5);
        List<UserPo> list = userDao.findByPage(map);
        for (UserPo userPo : list) {
            System.out.println(userPo);
        }
    }
}
