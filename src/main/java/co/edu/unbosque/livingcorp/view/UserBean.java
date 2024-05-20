package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.exception.BlockedUserException;
import co.edu.unbosque.livingcorp.model.exception.InvalidPasswordException;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UserBean.class);

    private UserDTO user;
    private List<UserDTO> registeredUsers;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init(){
        user = new UserDTO();
        registeredUsers = userService.showUsers();
    }

    public String logIn(){
        try {
            if(userService.logInUser(user)){
                user = userService.findUser(user.getUserName());
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("userName", user.getUserName());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bienvenido"));
                return userService.redirect(user);
            }else{
                return "login.xhtml";
            }
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (BlockedUserException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return "error_404.xhtml";
        } catch (InvalidPasswordException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        return "login.xhtml";
    }

    public String signUpUser(){
        try {
            userService.signUpUser(user);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (InvalidPasswordException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cuenta de Cliente Creada"));
        return "search_property.xhtml";
    }
    public String signUpAdmin(){
        try {
            userService.signUpAdmin(user);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (InvalidPasswordException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cuenta de Administrador Creada"));
        return "login.xhtml";
    }

    public String closeSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index.xhtml";
    }

    public void verifySession() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if (user == null || user.getUserName() == null) {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<UserDTO> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(List<UserDTO> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}