package com.ssm.model;

public class NationalDetails {

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

    public int getNationalPageId() {
        return nationalPageId;
    }

    public void setNationalPageId(int nationalPageId) {
        this.nationalPageId = nationalPageId;
    }

    public int getSpareId() {
        return spareId;
    }

    public void setSpareId(int spareId) {
        this.spareId = spareId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLawTitle() {
        return lawTitle;
    }

    public void setLawTitle(String lawTitle) {
        this.lawTitle = lawTitle;
    }

    public String getPublishUnits() {
        return publishUnits;
    }

    public void setPublishUnits(String publishUnits) {
        this.publishUnits = publishUnits;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(String failureDate) {
        this.failureDate = failureDate;
    }

    public String getSourece() {
        return sourece;
    }

    public void setSourece(String sourece) {
        this.sourece = sourece;
    }

    public String getViewcontent() {
        return viewcontent;
    }

    public void setViewcontent(String viewcontent) {
        this.viewcontent = viewcontent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    //自增主键id
    private int id;
    //
    private String code;
    //部分分页数据 主键id
    private int nationalPageId;
    //抓取路径id
    private int spareId;
    //标题
    private String title;
    //创建时间
    private String createTime;


    //法规标题
    private String lawTitle;
    //颁布单位
    private String publishUnits;
    //发文字号
    private String number;
    //颁布时间
    private String publishDate;
    //失效时间
    private String failureDate;
    //法规来源
    private String sourece;

    //正文
    private String viewcontent;


}
