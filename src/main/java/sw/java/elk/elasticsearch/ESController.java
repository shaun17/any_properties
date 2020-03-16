package sw.java.elk.elasticsearch;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
}
