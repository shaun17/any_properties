package sw.java.elk.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.hash.Hash;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ESController {
    @Autowired
    RequestLogServiceImpl requestLogService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/es/insert")
    public void insert() {
        requestLogService.esInsert(10);
    }

    @RequestMapping("/es/findAll")
    public Object findAll() {
        Iterable<RequestLog> requestLogs = requestLogService.esFindAll();
        return requestLogs;
    }

    @RequestMapping("/es/findone")
    public Object findone() {
        Optional<RequestLog> requestLog = requestLogService.esSelectById(1584351244977L);
        RequestLog requestLog1 = requestLog.get();
        return requestLog1;
    }

    @RequestMapping("/es/update")
    public void update() {
        Optional<RequestLog> requestLog = requestLogService.esSelectById(1584351244977L);
        RequestLog requestLog1 = requestLog.get();
        requestLog1.setUserName("哇哈哈");
        requestLogService.esUpdateById(requestLog1);
    }

    @RequestMapping("/es/findbyes")
    public Object findbyes() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("哇哈哈")).build();
        List<RequestLog> requestLogs = elasticsearchTemplate.queryForList(searchQuery, RequestLog.class);
        return requestLogs;
    }

    @RequestMapping("/es/insertbyes")
    public Object insertbyes() {
        List queries = new ArrayList();
        AtomicInteger atomicInteger = new AtomicInteger(20);
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap() {{
                put("name", "tom " + chars.charAt(new Random().nextInt(26))+chars.charAt(new Random().nextInt(26))+ " "+chars.charAt(new Random().nextInt(26))+chars.charAt(new Random().nextInt(26)));
                put("age",atomicInteger.incrementAndGet());
                put("id",1000000L+new Random().nextInt(100));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(new Date());
                put("birthday",format);
                put("price",new Random().nextDouble());
            }};
            IndexQuery iq = new IndexQuery();
            iq.setIndexName("elasticseatchtemplate");
            iq.setType("estype");
            iq.setSource(JSONObject.toJSONString(map));
//            iq.setId(UUID.randomUUID().toString());
//            iq.setObject(map);
            queries.add(iq);
        }
        elasticsearchTemplate.bulkIndex(queries);
        return "ok";

    }
    @RequestMapping("/es/findbyest")
    public Object findbyest() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("哇哈哈")).build();
        List<AliasMetaData> elasticseatchtemplate = elasticsearchTemplate.queryForAlias("elasticseatchtemplate");
        return elasticseatchtemplate;
    }
    @RequestMapping("/es/delbyest")
    public Object delbyest() {
        boolean elasticseatchtemplate = elasticsearchTemplate.deleteIndex("elasticseatchtemplate");
        return elasticseatchtemplate;
    }
}
