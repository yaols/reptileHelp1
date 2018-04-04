package com.help;


import com.ssm.Dao.DBTools;
import com.ssm.MD5Helper;
import com.ssm.model.NationalUnits;
import com.ssm.service.INationalUnits;
import com.ssm.service.NationalUnitsImpl;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App {

    public static  void main(String [] args) throws IOException, InterruptedException {
        System.out.println("Hello World!");

        //String url="law_view.asp?id=613638";
        //crawNationalDetails.getNationalDetails(url);

        //crawNationalUnitsPage.crawNationalUnitsPage();
        //crawlNationalUnitsData();


        //批量插入测试
        //testBatchAdd.batchAdd();

        //分页查询 单位分页数据
        crawNationalUnitsPage.getNationalUnitsPage();
    }

    /*
    * 抓取国家法律法规单位数据
    * */
    private static void crawlNationalUnitsData() throws IOException {

        String url="http://www.law-lib.com/law/bbdw-zy.htm";
        Document doc = Jsoup.connect(url).get();
        Elements pElements = doc.select("div.law_df p");


        int groupId=1;
        int parentId=0;


        for (Element element : pElements) {
            /*获取当前标签纯文字*/
            String oneText=element.ownText();
            parentId=0;
            setNationalUnitsModel(oneText,"",groupId,parentId);

            /*子节点数据*/
            Elements elements=element.children();
            parentId=parentId+1;
            for (Element item:elements){
                //System.out.println(item.text()+"|"+item.attr("href"));
                setNationalUnitsModel(item.text(),item.attr("href"),groupId,parentId);
            }

            groupId++;
        }

        System.out.println("抓取国家部门数据完毕");

    }

    /*
    * 国家法律法规实体
    * */
    //private static INationalUnits service = new NationalUnitsImpl();
    private static void setNationalUnitsModel(String name,String jumpUrl,int groupId,
                                                int parentId){
        NationalUnits model=new NationalUnits();
        model.setName(name);
        model.setGroupId(groupId);
        model.setParentId(parentId);
        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.setCreateTime(sdf.format(d));
        model.setJumpUrl(jumpUrl);
        model.setStates(1);
        model.setCode(MD5Helper.uuidEncrypt16());

        SqlSession sqlSession = DBTools.getSession();


        try {
            sqlSession.insert("insertNationalunits",model);
            System.out.println(model.toString());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }


        //service.addNationalUnits(model);

    }




}
