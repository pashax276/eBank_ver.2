package services.impl;

import dao.UserDao;
import domain.UserEntity;
import org.primefaces.component.inputtext.InputText;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

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
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("userExistsMsg"), userEntity.getUserName()), null);
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

    public boolean checkAvailable(AjaxBehaviorEvent event) {
        InputText inputText = (InputText) event.getSource();
        String value = (String) inputText.getValue();

        boolean available = userDao.checkAvaliable(value);

        if (!available) {
            FacesMessage message = constructErrorMessage(null, String.format(getMessageBundle().getString("userExistsMsg"), value));
            getFacesContext().addMessage(event.getComponent().getClientId(), message);
        } else {
            FacesMessage message = constractInfoMessage(null, String.format(getMessageBundle().getString("userAvailableMsg"), value));
            getFacesContext().addMessage(event.getComponent().getClientId(), message);
        }
        return available;
    }

    public UserEntity loadUserEntityByUsername(String userName) {
        return userDao.loadUserByUserName(userName);
    }

    /**
     * Construct UserDetails instance required by spring security
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity user = userDao.loadUserByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("badCredentials"), userName);
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

    protected ResourceBundle getMessageBundle(){
        return ResourceBundle.getBundle("message-labels");
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
