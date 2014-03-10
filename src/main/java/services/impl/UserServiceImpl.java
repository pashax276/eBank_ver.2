package services.impl;

import dao.UserDao;
import domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import services.UserService;

/**
 * Created by Павел on 09.03.14.
 */
public class UserServiceImpl implements UserService, UserDetailsService {

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

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return null;
    }
}
