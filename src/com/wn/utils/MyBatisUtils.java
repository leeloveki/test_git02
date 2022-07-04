package com.wn.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yellow Docter
 * @date 2022 -03-30
 * @desc
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String resource = "mybatis_config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装 sqlSession的获取
     * 每次连接数据库 sqlSession  需要重新获取
     *
     * @return
     */
    public static SqlSession getSqlSession(){
        //获取sqlSession

        //sqlSessionFactory.openSession(true) 自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        return sqlSession;
    }

    public static void close(SqlSession sqlSession){
        if (sqlSession != null){
            sqlSession.close();
        }
    }
}
