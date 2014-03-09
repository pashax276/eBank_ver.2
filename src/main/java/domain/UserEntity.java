package domain;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import java.io.Serializable;

/**
 * Created by Павел on 09.03.14.
 */
public class UserEntity implements Serializable{


    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordEncoder crypto = new Md5PasswordEncoder();
        this.password = crypto.encodePassword(password, null);
    }
}
