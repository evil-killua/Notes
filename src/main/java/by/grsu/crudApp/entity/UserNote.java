package by.grsu.crudApp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_Note",uniqueConstraints = {
        @UniqueConstraint(name = "User_Note_UK",columnNames = {"User_Id", "Note_Id"}) })
public class UserNote {

    @Id
    @GeneratedValue
    @Column(name = "Id",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Note_Id",nullable = false)
    private Note note;
}
