package by.grsu.crudApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "App_User",uniqueConstraints = {
        @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name")})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "User_Id",nullable = false)
    private Long id;

    @Column(name = "User_Name",length = 36,nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String password;

    @Column(name = "Enabled", length = 5, nullable = false)
    private int enable;


}
