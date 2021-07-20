package by.grsu.crudApp.entity;

import by.grsu.crudApp.services.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_Note",
        uniqueConstraints = {
            @UniqueConstraint(name = "APP_NOTE_UK",columnNames = "Note_Name")})
public class Note {

    @Id
    @GeneratedValue
    @Column(name = "Note_Id",nullable = false)
    private Long id;

    @Column(name = "Note_Name",length = 36,nullable = false)
    private String noteName;

    @Column(name = "Note_Text",length = 200,nullable = false)
    private String noteText;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "Date_Of_Creation", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private /*String*/ LocalDateTime /*LocalDate*/ dateOfCreation;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "Last_Change", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private /*String*/ LocalDateTime /*LocalDate*/ lastChange;


}
