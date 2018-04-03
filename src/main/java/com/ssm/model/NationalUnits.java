package com.ssm.model;
/*
*
* yaols 20180223  部门单位实体
*
* */
public class NationalUnits {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //单位部门名称
    private String name;

    //跳转url
    private String jumpUrl;

    //id
    private int id;

    //分组id
    private int groupId;

    //
    private int parentId;

    //创建时间
    private String createTime;

    //状态
    private int states;

    //唯一标识
    private String code;


}
