package com.peaksoft.entities.group;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    private String image;

    @ManyToMany(cascade = {DETACH,MERGE,REFRESH,REMOVE},fetch = FetchType.LAZY, mappedBy = "groups")
    private List<Course> courses;

    @OneToMany(cascade = ALL)
    private List<Student> studentList = new ArrayList<>();
    public void addCourse(Course course){
        if(courses == null)
            courses = new ArrayList<>();
    this.courses.add(course);
    }
//    public Course course(){
//        Course course = getCourses().stream().findAny().get();
//        createGroup();
//    }
}
