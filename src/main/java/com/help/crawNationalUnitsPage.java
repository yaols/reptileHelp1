package com.help;

import com.ssm.Dao.DBTools;
import com.ssm.MD5Helper;
import com.ssm.model.NationalPage;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        for (int i=29;i<50;i++){
            url=GetPageUrl(i+1);
            crawlNationalUnitsData(url);
            Thread.sleep(2000);
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

        for (Element element : pElements) {

            fisrtElement=element.child(0);
            firstHref=fisrtElement.attr("href");
            firstText=fisrtElement.text();

            lastText=element.child(1).text();
            NationalPage model=setNationalPageModel(firstText,lastText,firstHref);
            addNationalPage(model);
        }

    }


    /***
     * 部门分页实体数据库新增
     * @param model
     */
    private static void addNationalPage(NationalPage model){
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
    private static NationalPage setNationalPageModel(String title,String descript,String jumpUrl){
        NationalPage model =new NationalPage();

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

}
