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

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-17 22:54:13)
 */
public class SolrTest {

    private SolrServer solrServer = null;

    @Before
    public void setUp() throws Exception {
        solrServer = new HttpSolrServer("http://121.42.52.89:10020/solr");
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

        Solr solr=new Solr();
        solr.setId(4);
        solr.setName("111");
        solrServer.addBean(solr);
        solrServer.commit();

    }
}
