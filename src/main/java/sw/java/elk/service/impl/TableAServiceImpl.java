package sw.java.elk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sw.java.elk.dao.TableADao;
import sw.java.elk.service.TableAService;

import java.util.List;
import java.util.Map;

@Service
public class TableAServiceImpl implements TableAService {

    @Autowired
    TableADao tableADao;

    @Override
    public List<Map<String, Object>> queryList() {
        return tableADao.queryList();
    }
}
