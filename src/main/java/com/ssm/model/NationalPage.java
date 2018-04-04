package com.ssm.model;

import java.util.Date;

/***
 * 部分分页
 */
public class NationalPage {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public int getState() {
        return states;
    }

    public void setState(int state) {
        this.states = state;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }



    public int getSpareId() {
        return spareId;
    }

    public void setSpareId(int spareId) {
        this.spareId = spareId;
    }


    //主键id
    private int id;
    //唯一标识
    private String code;
    //关键字搜索
    private String keyWords;
    //标题
    private String title;
    //描述
    private String descript;
    //创建时间
    private String createTime;
    //部分名称id
    private int nationalId;
    //跳转链接
    private String jumpUrl;
    //状态
    private int states;
    //抓取路径id
    private int spareId;
}
