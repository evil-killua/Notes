package by.grsu.crudApp.Forms;


import by.grsu.crudApp.entity.Role;
import by.grsu.crudApp.entity.User;
import lombok.Data;

@Data
public class UserRoleForm {

    private Long idUser;
    private String role;
}
