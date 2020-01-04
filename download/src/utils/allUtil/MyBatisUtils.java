package utils.allUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2020 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年01月02日
 *
 * @author 徐威
 * @version : 1.0
 */
public class MyBatisUtils { // MyBatis获取连接的工具类
    private static SqlSession sqlSession;
    static {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            // 加载配置文件 MyBatis自带数据库连接池
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-3-cfg.xml"));
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 得到 SqlSession
    public static SqlSession getSqlSession() {
        return sqlSession;
    }
}
