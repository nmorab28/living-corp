package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.exception.BlockedUserException;
import co.edu.unbosque.livingcorp.model.exception.InvalidPasswordException;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PersistenceDAO<User, String> userDAO;

    private ModelMapper mp;

    public UserService(){
        mp = new ModelMapper();
    }

    public UserDTO signUpUser(UserDTO user) throws RepeatedObjectException, InvalidPasswordException {
        try {
            String hashedPassword = hashPassword(user.getUserPassword());
            user.setUserPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidPasswordException("Contraseña invalida");
        }
        user.setLoginAttempts(0);
        user.setLastLogin(LocalDateTime.now());
        user.setBlocked(false);
        user.setPropertyAdmin(false);
        user.setResidentPropertyOwner(false);
        return mp.map(userDAO.save(mp.map(user, User.class)), UserDTO.class);
    }

    public UserDTO signUpAdmin(UserDTO user) throws RepeatedObjectException, InvalidPasswordException {
        try {
            String hashedPassword = hashPassword(user.getUserPassword());
            user.setUserPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidPasswordException("Contraseña invalida");
        }
        user.setLoginAttempts(0);
        user.setLastLogin(LocalDateTime.now());
        user.setBlocked(false);
        user.setPropertyAdmin(true);
        user.setResidentPropertyOwner(false);
        return mp.map(userDAO.save(mp.map(user, User.class)), UserDTO.class);
    }

    public boolean logInUser(UserDTO user) throws ObjectNotFoundException, BlockedUserException, InvalidPasswordException {
        UserDTO verifiedUser = mp.map(userDAO.find(user.getUserName()), UserDTO.class);
        if(verifiedUser != null){
            if(verifiedUser.isBlocked()){
                throw new BlockedUserException("Usuario bloqueado por intentos");
            }
            try {
                if(verifyPassword(user.getUserPassword(), verifiedUser.getUserPassword())){
                    verifiedUser.setLoginAttempts(0);
                    verifiedUser.setLastLogin(LocalDateTime.now());
                    userDAO.update(mp.map(verifiedUser, User.class));
                    return true;
                }else {
                    verifiedUser.setLoginAttempts(verifiedUser.getLoginAttempts() + 1);
                    if (verifiedUser.getLoginAttempts() >= 3) {
                        verifiedUser.setBlocked(true);
                    }
                    userDAO.update(mp.map(verifiedUser, User.class));
                    if(verifiedUser.isBlocked()){
                        throw new BlockedUserException("Usuario bloqueado por intentos");
                    }
                    return false;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new InvalidPasswordException("Contraseña invalida");
            }
        } else {
            throw new ObjectNotFoundException("Usuario no encontrado");
        }
    }

    public String redirect(UserDTO user){
        if(user.isPropertyAdmin()){
            return "index_admin.xhtml";
        }
        return "index_user.xhtml";
    }

    public UserDTO findUser(String userName) throws ObjectNotFoundException {
        return mp.map(userDAO.find(userName), UserDTO.class) ;
    }

    public List<UserDTO> showUsers(){
        return userDAO.findAll().stream().map(user -> mp.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> showAdmins(){
        return userDAO.findAll().stream().filter(User :: isPropertyAdmin).map(user -> mp.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        return hashPassword(password).equals(hashedPassword);
    }

}
