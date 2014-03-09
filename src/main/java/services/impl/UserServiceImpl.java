package services.impl;

import dao.UserDao;
import domain.UserEntity;
import services.UserService;

/**
 * Created by Павел on 09.03.14.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public boolean createUser(UserEntity userEntity) {
        userDao.save(userEntity);
        return true;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
