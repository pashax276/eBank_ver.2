package services;

import domain.UserEntity;

/**
 * Created by Павел on 09.03.14.
 */
public interface UserService {

    boolean createUser(UserEntity userEntity);
}