package com.peaksoft.entities.course;

import com.peaksoft.entities.company.Company;
import com.peaksoft.entities.lesson.Lesson;
import com.peaksoft.entities.group.Group;
import com.peaksoft.entities.instructor.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String courseName;
    @Column
    private int duration;
    //    @ManyToOne(cascade = CascadeType{}, mappedBy = "courseList")
//    private Company company;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST, REMOVE}, fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Group> groups = new ArrayList<>();

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany
    private List<Lesson> lessons = new ArrayList<>();

}
