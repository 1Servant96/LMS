package com.peaksoft.entities.company;

import com.peaksoft.entities.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String companyName;
    @Column
    private String locatedCountry;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Course> courses;
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
    public int countAmountOfStudentsInCompany(){
        return (int) courses.stream().map(x -> x.getGroups().stream().map(y -> y.getStudentList().size())).count();
    }
}
