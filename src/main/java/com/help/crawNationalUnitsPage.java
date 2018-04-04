package com.help;

import com.ssm.Dao.DBTools;
import com.ssm.MD5Helper;
import com.ssm.model.NationalDetails;
import com.ssm.model.NationalPage;
import com.ssm.service.NationalPageMapper;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 部门单位分页抓取
 * yaols
 *
 */
public class crawNationalUnitsPage {

    /*
     *
     *抓取国家法律法规单位分页
     * */
    public static void crawNationalUnitsPage() throws IOException, InterruptedException {


        String url="";
//        url=GetPageUrl(1);
//        crawlNationalUnitsData(url);


        for (int i=0;i<50;i++){
            url=GetPageUrl(i+1);
            crawlNationalUnitsData(url);
            Thread.sleep(3000);
        }



        System.out.println("全国人民代表大会抓取完毕");
    }


    /***
     * 获取分页url
     * @return
     */
    private static String GetPageUrl(int pageIndex){
        //int pageindex=1;
        StringBuilder builder=new StringBuilder();
        builder.append("http://www.law-lib.com/law/lawml.asp");
        builder.append("?bbdw=%C8%AB%B9%FA%C8%CB%C3%F1%B4%FA%B1%ED%B4%F3%BB%E1&");
        builder.append(String.format("pages=%s&tm1=&tm2=",pageIndex));

        System.out.println(builder.toString());
        return builder.toString();
    }

    /*
     * 抓取国家法律法规单位数据
     * */
    private static void crawlNationalUnitsData(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements pElements = doc.select("ul.line2 li");

        //根元素
        Element fisrtElement;
        String firstHref="";
        String firstText="";
        String lastText="";

        List<com.ssm.model.NationalPage> NationalPages=new ArrayList<com.ssm.model.NationalPage>();

        //int num=0;

        for (Element element : pElements) {
            //num++;
            fisrtElement=element.child(0);
            firstHref=fisrtElement.attr("href");
            firstText=fisrtElement.text();

            lastText=element.child(1).text();
            com.ssm.model.NationalPage model=setNationalPageModel(firstText,lastText,firstHref);
            NationalPages.add(model);


            //每次批量插入20个  然后清空集合
            if(NationalPages.size()==20){
                System.out.println("批量插入数据开始:"+NationalPages.size());
                batchNationalPage(NationalPages);
                NationalPages.clear();
                System.out.println("批量插入数据结束:"+NationalPages.size());
            }
        }

    }


    /***
     * 批量插入  20条插入一次
     * @param list
     */
    private static void batchNationalPage(List<com.ssm.model.NationalPage>list){

        SqlSession sqlSession = DBTools.getSession();

        NationalPageMapper nationalMapper = sqlSession.getMapper(NationalPageMapper.class);

        try {
            nationalMapper.batchNationalPage(list);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }

    /***
     * 部门分页实体数据库新增
     * @param model
     */
    private static void addNationalPage(com.ssm.model.NationalPage model){
        SqlSession sqlSession = DBTools.getSession();

        try {
            sqlSession.insert("insertnationalpage",model);
            //System.out.println(model.toString());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

    /***
     * 部门分页实体
     * @param title 标题
     * @param descript  描述
     * @return
     */
    private static com.ssm.model.NationalPage setNationalPageModel(String title, String descript, String jumpUrl){
        com.ssm.model.NationalPage model =new com.ssm.model.NationalPage();

        model.setCode(MD5Helper.uuidEncrypt16());
        model.setKeyWords("全国人民代表大会");
        model.setNationalId(2);
        model.setTitle(title);
        model.setDescript(descript);
        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.setCreateTime(sdf.format(d));
        model.setState(1);
        model.setJumpUrl(jumpUrl);
        return model;
    }

    /***
     * 分页查询mysql数据库
     */
    public static void getNationalUnitsPage() throws IOException, InterruptedException {
        int start=1;
        int pageSize=10;
        List<NationalPage> list=new ArrayList<NationalPage>();

        for (int i=1;i<3;i++){
            start=pageSize*(i-1);
            list=nationalPageList(start,pageSize);

            for (NationalPage item : list){
                crawNationalDetails.getNationalDetails(item.getJumpUrl());
            }

            //System.out.println("加载数据条数"+list.size());
        }
    }

    /***
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    private static List<NationalPage> nationalPageList(int start,int pageSize){
        List<NationalPage> list =new ArrayList<NationalPage>();
        SqlSession sqlSession = DBTools.getSession();

        NationalPageMapper nationalMapper = sqlSession.getMapper(NationalPageMapper.class);

        try {
            list=nationalMapper.selectAllNationalPage(start,pageSize);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }

        return list;
    }


}
