package com.peaksoft.entities.company;

import com.peaksoft.entities.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the company name couldn't be empty")
    @Size(min = 2, message = "the company name couldn't be less than 2 letters")
    @Column
    private String companyName;
    @Column
    @NotEmpty(message = "the company name couldn't be empty")
    @Size(min = 2, message = "the company name couldn't be less than 2 letters")
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
