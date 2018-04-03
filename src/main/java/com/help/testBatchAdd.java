package com.help;

import com.ssm.Dao.DBTools;
import com.ssm.model.Test;
import com.ssm.service.TestMapperInterface;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.select.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class testBatchAdd {

    public static void batchAdd(){
        SqlSession sqlSession = DBTools.getSession();

        TestMapperInterface testMapper = sqlSession.getMapper(TestMapperInterface.class);

        List<Test> tests = new ArrayList<Test>();
        Test test1=new Test();
        test1.setName("zs");
        test1.setAge(16);
        tests.add(test1);

        test1=new Test();
        test1.setName("ww");
        test1.setAge(22);
        tests.add(test1);
        //Test test=new Test(3,"ww",12);


        try {
            testMapper.batchTest(tests);
            //testMapper.insertTest(test);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

    }

}
