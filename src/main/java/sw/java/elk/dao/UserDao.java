package sw.java.elk.dao;

import org.apache.ibatis.annotations.Mapper;
import sw.java.elk.po.User;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserDao {
    int insert(User user);
    User findByName(String username);

}
