package com.peaksoft.entities.course;

import com.peaksoft.entities.company.Company;
import com.peaksoft.entities.group.Group;
import com.peaksoft.entities.instructor.Instructor;
import com.peaksoft.entities.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table (name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "course's name should not be empty")
    @Size(min = 2, max = 50, message = "name should be between 2 and 50 characters")
    @Column
    private String courseName;
    @Column
//    @NotEmpty(message = "course duration should be entered")
//    @Size(min = 1, max = 9, message = "the course duration should more then 1 month and less than 9 month")
    private int duration;
    @NotEmpty(message = "Course description cant be null")
    @Column
    private String description;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH,PERSIST}, fetch = EAGER)
    private Company company;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST}, fetch = LAZY)
    private List<Group> groups;

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;
    public void addGroup(Group group){
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }
    public void addInstructor(Instructor instructor){
        if (instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }
    public void addLesson(Lesson lesson){
        if (lessons==null){
            lessons=new ArrayList<>();
        }
        lessons.add(lesson);
    }
}
