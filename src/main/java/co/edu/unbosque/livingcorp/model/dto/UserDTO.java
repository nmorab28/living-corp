package co.edu.unbosque.livingcorp.model.dto;

import co.edu.unbosque.livingcorp.model.entity.Resident;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {

    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime lastLogin;
    private int loginAttempts;
    private boolean isBlocked;
    private boolean isPropertyAdmin;
    private boolean isResidentPropertyOwner;
    private List<ResidentDTO> residents;

    public UserDTO() {}

    public UserDTO(String userName, String userEmail, String userPassword, LocalDateTime lastLogin, int loginAttempts, boolean isBlocked, boolean isPropertyAdmin, boolean isResidentPropertyOwner) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.lastLogin = lastLogin;
        this.loginAttempts = loginAttempts;
        this.isBlocked = isBlocked;
        this.isPropertyAdmin = isPropertyAdmin;
        this.isResidentPropertyOwner = isResidentPropertyOwner;
    }

    public UserDTO(String userName, String userEmail, String userPassword, LocalDateTime lastLogin, int loginAttempts, boolean isBlocked, boolean isPropertyAdmin, boolean isResidentPropertyOwner, List<ResidentDTO> residents) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.lastLogin = lastLogin;
        this.loginAttempts = loginAttempts;
        this.isBlocked = isBlocked;
        this.isPropertyAdmin = isPropertyAdmin;
        this.isResidentPropertyOwner = isResidentPropertyOwner;
        this.residents = residents;
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

    public List<ResidentDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentDTO> residents) {
        this.residents = residents;
    }
}
