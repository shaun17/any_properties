package sw.java.elk.service.primary;

import sw.java.elk.po.User;

public interface UserService {
    int insert(User user);
    User findByName(String username);

}
