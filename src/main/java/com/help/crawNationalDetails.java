package com.help;


import com.ssm.Dao.DBTools;
import com.ssm.MD5Helper;
import com.ssm.model.NationalDetails;
import com.ssm.model.NationalPage;
import com.ssm.service.NationalPageDetailsMapper;
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
 *Some regulations details
 * 部分法规详情
 */
public class crawNationalDetails {

    private static List<NationalDetails> NationalDetailsList=new ArrayList<NationalDetails>();

    /***
     * Get body details data based on URL
     * 根据URL 获取正文详情数据
     * @param url
     * @param detailsId 抓取数据url?id=
     * @param pageId 数据库主键id
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getNationalDetails(String url,int detailsId,int pageId) throws IOException, InterruptedException {

        Thread.sleep(3000);
        //String[]  strs=url.split("=");
        url=getUrl(url);
        NationalDetails details= crawlNationalDetailsData(url,detailsId,pageId);
        NationalDetailsList.add(details);

        if(NationalDetailsList.size()==10){
            addNationalPage(NationalDetailsList);
            NationalDetailsList.clear();
        }

    }


    /***
     *Request link suffix splicing
     * 请求链接后缀拼接
     * @param urlSuffix
     * @return
     */
    private static String getUrl(String urlSuffix){
        StringBuilder builder=new StringBuilder();
        builder.append("http://www.law-lib.com/law/");
        builder.append(urlSuffix);
        return builder.toString();
    }

    /***
     * Grab national laws and regulations unit details data
     * 抓取国家法律法规单位详情数据
     * @param url
     * @param detailsId
     * @param pageId
     * @return
     * @throws IOException
     */
    private static NationalDetails crawlNationalDetailsData(String url,int detailsId,int pageId) throws IOException {

        Document doc = Jsoup.connect(url).header("Connection", "close")//如果是这种方式，这里务必带上
                .timeout(8000).get();


        NationalDetails details=new NationalDetails();

        //标题
        Elements titleElements = doc.select("h3.title");
        details.setTitle(titleElements.text());

        //ol li
        Elements olElements = doc.select("div.law ol li");
        details.setLawTitle(olElements.eq(0).text());
        details.setPublishUnits(olElements.eq(1).text());
        details.setNumber(olElements.eq(2).text());
        details.setPublishDate(olElements.eq(3).text());
        details.setFailureDate(olElements.eq(4).text());
        details.setSourece(olElements.eq(5).text());


        //正文
        Elements contentElements = doc.select("div.viewcontent");
        details.setViewcontent(contentElements.text());

        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        details.setCreateTime(sdf.format(d));
        details.setCode(MD5Helper.uuidEncrypt16());
        details.setNationalPageId(pageId);
        details.setSpareId(detailsId);
        return details;
    }


    /***
     *Department paging entity database new
     * 部门分页实体数据库新增
     * @param NationalDetailsList
     */
    private static void addNationalPage(List<NationalDetails> NationalDetailsList){
        SqlSession sqlSession = DBTools.getSession();
        NationalPageDetailsMapper detailsMapper = sqlSession.getMapper(NationalPageDetailsMapper.class);

        try {
            detailsMapper.batchNationalPageDetails(NationalDetailsList);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
    }

}
