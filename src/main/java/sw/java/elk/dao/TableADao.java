package sw.java.elk.dao;

import org.apache.ibatis.annotations.Mapper;
import sw.java.elk.po.TableA;
import sw.java.elk.po.User;

import java.util.List;
import java.util.Map;

public interface TableADao {
    List<Map<String,Object>> queryList();

    int insert(TableA tableA);

}
