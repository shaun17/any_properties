package sw.java.elk.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface TableADao {
    List<Map<String,Object>> queryList();
}
