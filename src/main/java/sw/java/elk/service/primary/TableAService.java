package sw.java.elk.service.primary;

import sw.java.elk.po.TableA;

import java.util.List;
import java.util.Map;

public interface TableAService {
    List<Map<String,Object>> queryList();
    int insertOne(TableA tableA);
}

