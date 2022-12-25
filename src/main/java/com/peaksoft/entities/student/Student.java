package com.peaksoft.entities.student;

import com.peaksoft.entities.group.Group;
import com.peaksoft.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the first name couldn't be empty")
    @Size(min = 2, message = "the first name shouldn't be less than 2")
    private String firstName;
    @NotEmpty(message = "the last name couldn't be empty")
    @Size(min = 2, message = "the last name shouldn't be less than 2")
    private String lastName;
    @NotEmpty(message = "the phone number name couldn't be empty")
    @Size(min = 2, message = "the phone number couldn't be less than 2 letters")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty(message = "you should to choose the format")
    private StudyFormat studyFormat;
    @OneToOne(cascade = CascadeType.ALL)
    private Group group;

    public Student(Long id, String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }

    public void addStudentToGroup(Student student){
        if(group.getStudentList().stream().noneMatch(x -> Objects.equals(x.id, student.id))){
            group.getStudentList().add(student);
            student.setGroup(group);
        };
    }


}
