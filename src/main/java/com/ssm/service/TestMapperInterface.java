package com.ssm.service;

import com.ssm.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapperInterface {


    public void batchTest(@Param("tests") List<Test> tests);

    /**
     * 插入user
     */
//    @Insert("insert into test(id,name,age) values(#{id},#{name},#{age}) ")
    public int insertTest(Test test);

}
