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
    public  void  batchNationalPage(@Param("nationals") List<com.ssm.model.NationalPage> nationals);

}
