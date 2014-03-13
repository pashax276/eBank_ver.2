package services;

import domain.UserEntity;

import javax.faces.event.AjaxBehaviorEvent;

/**
 * Created by Павел on 09.03.14.
 */
public interface UserService {

    boolean createUser(UserEntity userEntity);

    boolean checkAvailable(AjaxBehaviorEvent event);

    UserEntity loadUserEntityByUsername(String userName);
}