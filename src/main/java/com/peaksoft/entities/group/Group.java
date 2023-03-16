package com.peaksoft.entities.group;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private long id;
    @NotEmpty(message = "the group name couldn't be empty")
    @Size(min = 2, message = "the group name couldn't be less than 2 letters")
    private String groupName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;

    private String image;


    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,REMOVE}, fetch = FetchType.LAZY,mappedBy = "groups")
    private List<Course> courses;

    @OneToMany(cascade = ALL,fetch = FetchType.LAZY,mappedBy = "group")
    private List<Student> students = new ArrayList<>();
    public void addCourse(Course course){
        if (courses == null){
            courses=new ArrayList<>();
        }
     courses.add(course);
    }
    public void addStudent(Student student){
        students.add(student);
        for (Course c:courses) {
            c.getCompany().plusStudent();
        }
    }
}
