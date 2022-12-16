package com.peaksoft.entities.instructor;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.group.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH}, fetch = FetchType.EAGER)
    private Course course;

    public void addInstructorToCourse(Instructor instructor){
       if(course.getInstructors().stream().noneMatch(x -> Objects.equals(x.id, instructor.id))){
           course.getInstructors().add(instructor);
       };
    }

    public int countAmountOfStudentsInstructor(){
List <Group> list = course.getInstructors().stream().filter(x->x.getId().equals(id)).findAny().get().getCourse().getGroups();
int amount = list.stream().iterator().next().getStudentList().size();
   return amount; }
//    public void addInstructorToCourse(Course courseo){
//
//    }

}
