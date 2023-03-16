package com.peaksoft.entities.instructor;

import com.peaksoft.entities.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
//@Table (name = "instructors")

@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the first name couldn't be empty")
    @Size(min = 2, message = "the first name couldn't be less than 2 letters")
    private String firstName;
    @NotEmpty(message = "the last name couldn't be empty")
    @Size(min = 2, message = "the last name couldn't be less than 2 letters")
    private String lastName;
    @NotEmpty(message = "the phone number name couldn't be empty")
    @Size(min = 2, message = "the phone number couldn't be less than 2 letters")
    private String phoneNumber;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty(message = "the specialization couldn't be empty")
    @Size(min = 2, message = "the specialization couldn't be less than 2 letters")
    private String specialization;
    @ManyToOne(cascade = {MERGE, DETACH, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    private Course course;
    private int students;

    public void plus(){
        students++;
    }

    public void minus(){
        students--;
    }
    public int countAmountOfStudentsInstructor(){
        int sum = 0;
        if (course != null) {
            sum = (int) (course).getGroups().stream().map(x -> x.getStudents().size()).count();
        }
        return sum;
    }
}
