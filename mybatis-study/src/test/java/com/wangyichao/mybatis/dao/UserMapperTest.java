package com.wangyichao.mybatis.dao;

import com.wangyichao.mybatis.bean.User;
import com.wangyichao.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {

    @Test
    public void test() {
        //获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        //方式一：执行sql
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }
}
