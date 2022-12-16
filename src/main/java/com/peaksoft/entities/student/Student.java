package com.peaksoft.entities.student;

import com.peaksoft.entities.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Enum studyFormat;
    @OneToOne(cascade = CascadeType.ALL)
    private Group group;

    public Student(Long id, String firstName, String lastName, String phoneNumber, String email, Enum studyFormat) {
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
