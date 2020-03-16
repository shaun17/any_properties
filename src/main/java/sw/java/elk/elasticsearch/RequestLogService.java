package sw.java.elk.elasticsearch;

import java.util.Optional;

public interface RequestLogService {
    public String esInsert(Integer num) ;
    public Iterable<RequestLog> esFindAll ();
    public String esUpdateById(RequestLog requestLog);
    public Optional<RequestLog> esSelectById(Long id);
    public Iterable<RequestLog> esFindOrder();
    public Iterable<RequestLog> esFindOrders();
    public Iterable<RequestLog> search();


}
