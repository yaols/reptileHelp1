package com.ssm.service;

import com.ssm.model.NationalPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationalPageMapper {

    /***
     * 批量插入
     * @param nationals
     * @return
     */
    public  void  batchNationalPage(@Param("nationals") List<NationalPage> nationals);


    /***
     * 分页查询部门分页数据
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<NationalPage> selectAllNationalPage(@Param("start")int start,@Param("pageSize")int pageSize) throws Exception;
}
