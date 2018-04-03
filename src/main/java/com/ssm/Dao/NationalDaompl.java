package com.ssm.Dao;

import com.ssm.Mapper.UserMapper;
import com.ssm.model.NationalUnits;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.Reader;

public class NationalDaompl implements INationalDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private UserMapper mapper;

    public NationalDaompl(){
        String resource = "config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    *
    * */
    public void addNationalUnits(NationalUnits units) {
        mapper.addNationalUnits(units);
        session.commit();
    }

    //声明Connection对象
    Connection con;
    //驱动程序名
    String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名login
    String url = "jdbc:mysql://localhost:3306/reptiledata";
    //MySQL配置时的用户名
    String userName = "root";
    //MySQL配置时的密码
    String passWord = "sasa";


    public void addModel(NationalUnits units)  {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Connection connection = DriverManager.getConnection(url, userName, passWord);
            StringBuilder builder=new StringBuilder();
            builder.append("INSERT into nationalunits(Name,JumpUrl,GroupId,ParentId,");
            builder.append("CreateTime,States) values (?,?,?,?,?,?);");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    builder.toString(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, units.getName());
            preparedStatement.setString(2, units.getJumpUrl());
            preparedStatement.setInt(3, units.getGroupId());
            preparedStatement.setInt(4, units.getParentId());
            preparedStatement.setString(5, units.getCreateTime());
            preparedStatement.setInt(6, units.getStates());

            preparedStatement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
