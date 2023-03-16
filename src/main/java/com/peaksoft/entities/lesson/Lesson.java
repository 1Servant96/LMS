package com.peaksoft.entities.lesson;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table (name = "lessons")

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "the lesson name couldn't be empty")
    @Size(min = 2, message = "the lesson name shouldn't be less than 2")
    private String lessonName;
    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Course course;
    @OneToMany(cascade = {ALL},fetch = FetchType.LAZY,mappedBy = "lesson")
    private List<Task> tasks = new ArrayList<>();


    public void addTaskToLesson(Task task){
        if(tasks.stream().noneMatch(x -> Objects.equals(x.getId(), task.getId()))){
        tasks.add(task);
        }
    }
}
