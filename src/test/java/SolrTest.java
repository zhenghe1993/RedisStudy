import com.jmper.solr.Solr;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-17 22:54:13)
 */
public class SolrTest {

    private SolrServer solrServer = null;

    @Before
    public void setUp() throws Exception {
        solrServer = new HttpSolrServer("http://123.56.234.15:9901/solr");
    }

    @After
    public void tearDown() throws Exception {
//        solrServer.shutdown();
    }

    @Test
    public void insert() throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "1001");
        document.addField("name", "iphone6s手机");
        document.addField("price", "6000");
        document.addField("url", "/images/iphone6s.png");
        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("id", "1002");
        document2.addField("name", "iphone x手机");
        document2.addField("price", "8000");
        document2.addField("url", "/images/iphonex.png");

        solrServer.add(document);
        solrServer.add(document2);
        solrServer.commit();
    }


    @Test
    public void search() throws Exception {

        SolrQuery query = new SolrQuery();
        query.set("q", "name:手机");
        query.setSort("id", SolrQuery.ORDER.desc);
        QueryResponse response = solrServer.query(query);

        SolrDocumentList res = response.getResults();

        for (SolrDocument document : res) {
            System.out.println(document.get("id"));
            System.out.println(document.get("name"));
            System.out.println(document.get("price"));
            System.out.println(document.get("url"));
        }
    }

    @Test
    public void del() throws Exception {
        solrServer.deleteById("4");
        solrServer.commit();
    }


    @Test
    public void bean() throws Exception {

        Solr solr = new Solr();
        solr.setId(Integer.valueOf(1 + "" + 1));
        solr.setProgramId(1);
        solr.setProgramTitle("节目类型1的节目id=1的节目");
        solr.setSearchCount(0);
        solr.setType(1);
        solrServer.addBean(solr);
        solrServer.commit();
    }


    public static void main(String[] args) throws Exception {

        List<Integer> arr = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        //原始
        for (int i = 1; i < 366; i++) {
            arr.add(i);
        }

        //随机
        Random random = new Random();
        for (int i = 365; i > 0; i--) {
            int res = random.nextInt(i); //输出 0-i之间的随机整数
            Integer data = arr.get(res); //在arr中获取该随机数角标的整数
            result.add(data); //保存在另一集合中
            boolean flag = arr.remove(data); //删除arr中该整数
        }


        //打印
        int temp = 0;
        for (int i : result) {


            temp++;
            if (temp == 30) {
                System.out.println(i);
                temp = 0;
            } else {
                System.out.print(i + "-");
            }
        }

        System.out.println("------");
        switch (4) {
            default:
                System.out.println("default");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
            case 4:
                System.out.println("4");
        }


        try{
            System.out.println("----------1");
            throw new Exception("1");
        }catch (IOException e){
            System.out.println("----------2");
            throw new Exception("2");
        }catch (Exception e){
            System.out.println("----------3");
            throw new Exception("3");
        }finally {
            System.out.println("----------4");
            throw new Exception("4");
        }


    }
}
