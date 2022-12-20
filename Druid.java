package com.example.springproject.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.testng.annotations.Test;

public class Druid {
    //Druid德鲁伊,据说是魔兽世界中的一个角色,森林女神
    public static DruidDataSource dataSource;

    //1.初始化Druid连接池
    static {
        //1.硬编码初始化Druid连接池
        try {
            dataSource = new DruidDataSource();
            //四个基本属性
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/cs209_project");
            dataSource.setUsername("checker");
            dataSource.setPassword("123456");
            //其他属性
            //初始大小
            dataSource.setInitialSize(10);
            //最大大小
            dataSource.setMaxActive(50);
            //最小大小
            dataSource.setMinIdle(10);
            //检查时间
            dataSource.setMaxWait(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.获取连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DruidTest() {
        //超过最大限制或报"TimeoutException",每打开一个关闭一个就不会发生异常
        for (int i = 0; i < 51; i++) {
            Connection connection = Druid.getConnection();
            System.out.println(connection.toString() + "\n------------------------------------");
            Druid.closeAll(connection);
        }
    }


}

