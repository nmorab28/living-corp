package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="WEB_USERS")
public class User {

    @Id
    @Column(name="USER_NAME")
    private String userName;

    @Column(name="USER_EMAIL")
    private String userEmail;

    @Column (name = "USER_PASSWORD")
    private String userPassword;

    @Column (name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column (name = "LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column (name = "IS_BLOCKED")
    private boolean isBlocked;

    @Column (name = "IS_PROPERTY_ADMIN")
    private boolean isPropertyAdmin;

    @Column (name = "IS_RESIDENT_PPRTY_OWNER")
    private boolean isResidentPropertyOwner;

    public User() {}

    public User(String userName, String userEmail, String userPassword, LocalDateTime lastLogin, int loginAttempts, boolean isBlocked, boolean isPropertyAdmin, boolean isResidentPropertyOwner) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.lastLogin = lastLogin;
        this.loginAttempts = loginAttempts;
        this.isBlocked = isBlocked;
        this.isPropertyAdmin = isPropertyAdmin;
        this.isResidentPropertyOwner = isResidentPropertyOwner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isPropertyAdmin() {
        return isPropertyAdmin;
    }

    public void setPropertyAdmin(boolean propertyAdmin) {
        isPropertyAdmin = propertyAdmin;
    }

    public boolean isResidentPropertyOwner() {
        return isResidentPropertyOwner;
    }

    public void setResidentPropertyOwner(boolean residentPropertyOwner) {
        isResidentPropertyOwner = residentPropertyOwner;
    }
}
