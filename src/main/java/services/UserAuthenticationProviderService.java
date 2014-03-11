package services;

import domain.UserEntity;

/**
 * Created by РџР°РІРµР» on 10.03.14.
 */
public interface UserAuthenticationProviderService {
    boolean processUserAuthentication(UserEntity user);
}
