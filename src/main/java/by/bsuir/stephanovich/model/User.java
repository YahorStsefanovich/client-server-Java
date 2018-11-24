package by.bsuir.stephanovich.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

@XStreamAlias("Student")
public class User implements Serializable {

    @XStreamAsAttribute
    @XStreamAlias("login")
    private String login;

    @XStreamAsAttribute
    @XStreamAlias("password")
    private String password;

    @XStreamAsAttribute
    @XStreamAlias("isAdmin")
    private boolean isAdmin;

    public User(){

    }

    public User(String login, String password, boolean isAdmin){
        this.login = login;
        // DigestUtils.md5Hex
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
