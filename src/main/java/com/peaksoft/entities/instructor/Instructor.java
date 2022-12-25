package com.peaksoft.entities.instructor;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Getter
@Setter
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

    public void addInstructorToCourse(Instructor instructor) {
        if (course.getInstructors().stream().noneMatch(x -> Objects.equals(x.id, instructor.id))) {
            course.getInstructors().add(instructor);
        }
        ;
    }

    public int countAmountOfStudentsInstructor() {
        List<Group> list = course.getInstructors().stream().filter(x -> x.getId().equals(id)).findAny().get().getCourse().getGroups();
        return list.stream().iterator().next().getStudentList().size();
    }
//    public void addInstructorToCourse(Course courseo){
//
//    }

}
