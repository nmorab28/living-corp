package co.edu.unbosque.livingcorp.datamapper;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    public static UserDTO UserToDTO(User user){
        UserDTO dto = new UserDTO();
        return dto;
    }

    public static User DTOToUser(UserDTO dto){
        User user = new User();
        return user;
    }

    public static List<UserDTO> ListUsersToDTOs(List<User> users) {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        for(User user: users){
            dtos.add(UserToDTO(user));
        }
        return dtos;
    }
/*
    public static PropertyDTO PropertyToDTO(Property property){
        PropertyDTO dto = new PropertyDTO(property.getIdProperty(), property.getNameProperty(), property.getCity(), property.getAddress(), property.getArea(), property.getPriceProperty(), property.getRoom(), property.getDescription(), )
    }*/
}
