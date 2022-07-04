package com.wn.service.impl;

import com.wn.bean.User;
//import com.wn.mapper.StuMapper;
import com.wn.mapper.UserMapper;
import com.wn.service.UserService;
import com.wn.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean login(User user) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user02 = mapper.login(user);
        boolean flag=false;
        if(user02!=null){
            flag=true;
        }
        sqlSession.close();
        return flag;
    }

    @Override
    public boolean register(User user) {
        SqlSession sqlSession= MyBatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        boolean flag=false;
        //检测用户名是否存在
        Integer result=mapper.isUsernameExist(user.getUsername());
        if(result!=0){
            flag=false;
            sqlSession.close();
            return flag;
        }else{
            Integer result02 = mapper.register(user);
            sqlSession.close();
            return flag=true;
        }
//        return flag;
    }
}
