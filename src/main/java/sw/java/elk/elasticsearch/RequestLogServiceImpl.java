package sw.java.elk.elasticsearch;

import com.aliyun.oss.common.utils.DateUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RequestLogServiceImpl implements RequestLogService {

    @Resource
    private RequestLogRepository requestLogRepository ;
    @Override
    public String esInsert(Integer num) {

        for (int i = 0 ; i < num ; i++){
            RequestLog requestLog = new RequestLog() ;
            requestLog.setId(System.currentTimeMillis());
            requestLog.setOrderNo(System.currentTimeMillis()+"");
            requestLog.setUserId("userId"+i);
            requestLog.setUserName("张三"+i);
            requestLog.setCreateTime((System.currentTimeMillis()+""));
            requestLogRepository.save(requestLog) ;
        }
        return "success" ;
    }
    @Override
    public Iterable<RequestLog> esFindAll (){
        return requestLogRepository.findAll() ;
    }
    @Override
    public String esUpdateById(RequestLog requestLog) {
        requestLogRepository.save(requestLog);
        return "success" ;
    }
    @Override
    public Optional<RequestLog> esSelectById(Long id) {
        return requestLogRepository.findById(id) ;
    }


        @Override
    public Iterable<RequestLog> esFindOrder() {
        // 用户名倒序
        // Sort sort = new Sort(Sort.Direction.DESC,"userName.keyword") ;
        // 创建时间正序
//         Sort sort = new Sort(Sort.Direction.ASC,"createTime.keyword") ;
            return null;
//        return requestLogRepository.findAll(sort) ;
    }
    @Override
    public Iterable<RequestLog> esFindOrders() {
        List<Sort.Order> sortList = new ArrayList<>() ;
        Sort.Order sort1 = new Sort.Order(Sort.Direction.ASC,"createTime.keyword") ;
        Sort.Order sort2 = new Sort.Order(Sort.Direction.DESC,"userName.keyword") ;
        sortList.add(sort1) ;
        sortList.add(sort2) ;
        Sort orders = Sort.by(sortList) ;
        return requestLogRepository.findAll(orders) ;
    }
    @Override
    public Iterable<RequestLog> search() {
        // 全文搜索关键字
        /*
        String queryString="张三";
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        requestLogRepository.search(builder) ;
        */
        /*
         * 多条件查询
         */
        QueryBuilder builder = QueryBuilders.boolQuery()
                // .must(QueryBuilders.matchQuery("userName.keyword", "历张")) 搜索不到
                .must(QueryBuilders.matchQuery("userName", "张三")) // 可以搜索
                .must(QueryBuilders.matchQuery("orderNo", "20190613736278243"));
        return requestLogRepository.search(builder) ;
    }
}
