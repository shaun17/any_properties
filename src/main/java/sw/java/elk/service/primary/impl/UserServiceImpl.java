package sw.java.elk.service.primary.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sw.java.elk.dao.UserDao;
import sw.java.elk.po.User;
import sw.java.elk.service.primary.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int insert(User user) {
        int insert = userDao.insert(user);
        return insert;
    }

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }
}
