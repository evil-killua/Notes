package by.grsu.crudApp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "App_Role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name")})
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long id;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;

}
