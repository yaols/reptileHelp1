package com.ssm.service;

import com.ssm.model.NationalDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationalPageDetailsMapper {


    /***
     * 批量插入文章详情  10篇文章一次
     * @param NationalDetailsList
     * @return
     */
    public int batchNationalPageDetails(@Param("nationals") List<NationalDetails> NationalDetailsList);

}
