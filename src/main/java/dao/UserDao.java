package dao;

import commons.domain.dao.GenericDao;
import domain.UserEntity;

/**
 * Created by Павел on 09.03.14.
 */
public interface UserDao extends GenericDao<UserEntity, Long> {

    boolean checkAvaliable(String userName);
    UserEntity loadUserByUserName(String userName);
}
