package services.impl;

import dao.UserDao;
import domain.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Service providing service methods to work with user data and entity.
 */
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    /**
     * Create user - persist to database
     *
     * @param userEntity
     * @return true if success
     */
    public boolean createUser(UserEntity userEntity) {

        if (!userDao.checkAvaliable(userEntity.getUserName())) {
            FacesMessage message = constructErrorMessage(String.format("User Name '%s' is not available", userEntity.getUserName()), null);
            getFacesContext().addMessage(null, message);
            return false;
        }
        try {
            userDao.save(userEntity);
        } catch (Exception e) {
            FacesMessage message = constractFatalMessage(e.getMessage(), null);
            getFacesContext().addMessage(null, message);
            return false;
        }

        return true;
    }

    /**
     * Construct UserDetails instance required by spring security
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity user = userDao.loadUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No such user with name provided '%s'", userName));
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User userDetails = new User(user.getUserName(), user.getPassword(), authorities);

        return userDetails;
    }


    protected FacesMessage constructErrorMessage(String message, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail);
    }

    protected FacesMessage constractInfoMessage(String message, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
    }

    protected FacesMessage constractFatalMessage(String message, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail);
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
