package com.ssm.Mapper;

import com.ssm.model.NationalUnits;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {
    @Insert("INSERT into nationalunits(Name,JumpUrl,GroupId,ParentId,CreateTime,States) values (#{Name},#{JumpUrl},#{GroupId},#{ParentId},#{CreateTime},#{States})")
    void addNationalUnits(NationalUnits units);
}
