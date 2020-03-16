package sw.java.elk.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//用 data的注解 document 指定 索引名，和类型，这里为了测试方便，我随便起的名字
@Document(indexName = "javaestest",type = "aaa")
@Data
public class RequestLog {
    //Id注解Elasticsearch里相应于该列就是主键，查询时可以使用主键查询
    @Id
    private Long id;
    private String orderNo;
    private String userId;
    private String userName;
    private String createTime;
}